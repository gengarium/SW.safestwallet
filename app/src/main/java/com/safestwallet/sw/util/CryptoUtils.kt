package com.safestwallet.sw.util
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import timber.log.Timber
import java.security.KeyStore
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
/**
 * Utility per operazioni crittografiche
 */
object CryptoUtils {
    private const val ANDROID_KEYSTORE = "AndroidKeyStore"
    const val KEY_ALIAS = "SAFEST_WALLET_KEY"
    private const val AES_MODE = "AES/GCM/NoPadding"
    private const val IV_LENGTH = 12
    private const val TAG_LENGTH = 128
    
    /**
     * Genera una frase mnemonica casuale (seed phrase)
     */
    fun generateMnemonic(): String {
        // Implementazione base - in produzione usare una libreria BIP39 completa
        val words = arrayOf(
            "abandon", "ability", "able", "about", "above", "absent", "absorb", "abstract", 
            "absurd", "abuse", "access", "accident", "account", "accuse", "achieve", "acid",
            "acoustic", "acquire", "across", "act", "action", "actor", "actual", "adapt"
            // In una implementazione reale, includere tutti i 2048 words BIP39
        )
        
        val random = SecureRandom()
        return (1..12).joinToString(" ") { words[random.nextInt(words.size)] }
    }
    
    /**
     * Crea una chiave sicura nel keystore Android
     */
    fun createSecretKey(): Boolean {
        try {
            // Prima verifica se la chiave esiste già
            val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)
            keyStore.load(null)
            
            if (keyStore.containsAlias(KEY_ALIAS)) {
                Timber.d("Key already exists, removing old key")
                keyStore.deleteEntry(KEY_ALIAS)
            }
            
            val keyGenerator = KeyGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEYSTORE
            )
            
            val keyGenParameterSpec = KeyGenParameterSpec.Builder(
                KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            ).apply {
                setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                setKeySize(256)
                setUserAuthenticationRequired(false) // Cambiare a true per richiedere autenticazione
            }.build()
            
            keyGenerator.init(keyGenParameterSpec)
            keyGenerator.generateKey()
            Timber.d("Successfully created new key")
            return true
        } catch (e: Exception) {
            Timber.e(e, "Error creating secret key: ${e.message}")
            return false
        }
    }
    
    /**
     * Cifra dati utilizzando la chiave nel keystore
     */
    fun encrypt(plainText: String): String? {
        return try {
            val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)
            keyStore.load(null)
            
            val secretKey = keyStore.getKey(KEY_ALIAS, null) as SecretKey
            val cipher = Cipher.getInstance(AES_MODE)
            
            // Genera un IV casuale
            val iv = ByteArray(IV_LENGTH)
            SecureRandom().nextBytes(iv)
            
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, GCMParameterSpec(TAG_LENGTH, iv))
            
            val encryptedBytes = cipher.doFinal(plainText.toByteArray(Charsets.UTF_8))
            
            // Concatena IV e testo cifrato
            val combined = ByteArray(iv.size + encryptedBytes.size)
            System.arraycopy(iv, 0, combined, 0, iv.size)
            System.arraycopy(encryptedBytes, 0, combined, iv.size, encryptedBytes.size)
            
            Base64.encodeToString(combined, Base64.DEFAULT)
        } catch (e: Exception) {
            Timber.e(e, "Error encrypting data")
            null
        }
    }
    
    /**
     * Decifra dati utilizzando la chiave nel keystore
     */
    fun decrypt(encryptedText: String): String? {
        return try {
            val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)
            keyStore.load(null)
            
            val secretKey = keyStore.getKey(KEY_ALIAS, null) as SecretKey
            val cipher = Cipher.getInstance(AES_MODE)
            
            // Decodifica Base64
            val combined = Base64.decode(encryptedText, Base64.DEFAULT)
            
            // Estrai IV e testo cifrato
            val iv = ByteArray(IV_LENGTH)
            val encryptedBytes = ByteArray(combined.size - IV_LENGTH)
            
            System.arraycopy(combined, 0, iv, 0, iv.size)
            System.arraycopy(combined, iv.size, encryptedBytes, 0, encryptedBytes.size)
            
            cipher.init(Cipher.DECRYPT_MODE, secretKey, GCMParameterSpec(TAG_LENGTH, iv))
            
            String(cipher.doFinal(encryptedBytes), Charsets.UTF_8)
        } catch (e: Exception) {
            Timber.e(e, "Error decrypting data")
            null
        }
    }
    
    /**
     * Genera un codice OTP casuale di 6 cifre
     */
    fun generateOtp(): String {
        val random = SecureRandom()
        return String.format("%06d", random.nextInt(1000000))
    }
    
    /**
     * Calcola hash SHA-256 di una stringa
     */
    fun sha256(input: String): String {
        val bytes = input.toByteArray()
        val md = java.security.MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
    
    /**
     * Verifica se la chiave esiste nel keystore
     */
    fun keyExists(): Boolean {
        return try {
            val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)
            keyStore.load(null)
            keyStore.containsAlias(KEY_ALIAS)
        } catch (e: Exception) {
            Timber.e(e, "Error checking if key exists")
            false
        }
    }
    
    /**
     * Genera un OTP usando il seed
     */
    fun generateOtpFromSeed(seed: String): String {
        // Implementazione semplificata di OTP TOTP
        val currentTimeMillis = System.currentTimeMillis()
        val timeStep = 30 * 1000 // 30 secondi
        val t = currentTimeMillis / timeStep
        
        // Usa l'hash del seed+time come base per l'OTP
        val data = seed + t.toString()
        val hash = sha256(data)
        
        // Prendi le prime 6 cifre dall'hash
        val otp = hash.substring(0, 6).toIntOrNull(16)?.rem(1000000) ?: 0
        
        // Formatta come stringa di 6 cifre con padding zero
        return String.format("%06d", otp)
    }
}