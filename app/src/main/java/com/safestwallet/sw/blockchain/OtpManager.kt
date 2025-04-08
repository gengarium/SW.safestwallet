package com.safestwallet.sw.blockchain

import com.safestwallet.sw.util.CryptoUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * Gestisce la generazione e verifica dei codici OTP
 */
class OtpManager {
    
    /**
     * Genera un codice OTP usando il seed fornito
     * @param encryptedOtpSeed Il seed OTP cifrato
     * @return Il codice OTP generato o null in caso di errore
     */
    suspend fun generateOtp(encryptedOtpSeed: String): String? = withContext(Dispatchers.Default) {
        try {
            // Decifra il seed OTP
            val otpSeed = CryptoUtils.decrypt(encryptedOtpSeed)
                ?: throw Exception("Failed to decrypt OTP seed")
            
            // Genera l'OTP
            return@withContext CryptoUtils.generateOtpFromSeed(otpSeed)
        } catch (e: Exception) {
            Timber.e(e, "Error generating OTP")
            return@withContext null
        }
    }
    
    /**
     * Verifica se un codice OTP è valido
     * @param encryptedOtpSeed Il seed OTP cifrato
     * @param otp Il codice OTP da verificare
     * @return true se l'OTP è valido, false altrimenti
     */
    suspend fun verifyOtp(encryptedOtpSeed: String, otp: String): Boolean = withContext(Dispatchers.Default) {
        try {
            val generatedOtp = generateOtp(encryptedOtpSeed)
            
            if (generatedOtp != null) {
                // Confronta l'OTP generato con quello fornito
                return@withContext otp == generatedOtp
            }
            
            return@withContext false
        } catch (e: Exception) {
            Timber.e(e, "Error verifying OTP")
            return@withContext false
        }
    }
    
    /**
     * In un'implementazione reale, questo metodo invierebbe il seed OTP all'oracle Switchboard
     * Per ora, è solo un placeholder
     */
    suspend fun registerOtpWithSwitchboard(publicKey: String, encryptedOtpSeed: String): Boolean = withContext(Dispatchers.IO) {
        try {
            // Simulazione di registrazione con l'oracle
            Timber.d("Simulating OTP registration with Switchboard for: $publicKey")
            return@withContext true
        } catch (e: Exception) {
            Timber.e(e, "Error registering OTP with Switchboard")
            return@withContext false
        }
    }
}