package com.safestwallet.sw

import android.app.Application
import com.safestwallet.sw.blockchain.SolanaClient
import com.safestwallet.sw.data.AppDatabase
import com.safestwallet.sw.util.CryptoUtils
import com.safestwallet.sw.util.FileLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class SafestWalletApplication : Application() {
    
    // Database singleton
    val database by lazy { AppDatabase.getDatabase(this) }
    
    // Solana client singleton
    val solanaClient by lazy { SolanaClient() }
    
    override fun onCreate() {
        super.onCreate()
        
        // Inizializza Timber per il logging
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(FileLogger(this))
        }
        
        // Inizializza o ricrea la chiave di crittografia
        val cryptoJob = CoroutineScope(Dispatchers.IO).launch {
            Timber.i("Setting up encryption keys")
            
            try {
                // Force delete any existing key
                if (CryptoUtils.keyExists()) {
                    try {
                        val keyStore = java.security.KeyStore.getInstance("AndroidKeyStore")
                        keyStore.load(null)
                        keyStore.deleteEntry(CryptoUtils.KEY_ALIAS)
                        Timber.i("Deleted existing encryption key")
                    } catch (e: Exception) {
                        Timber.e(e, "Failed to delete existing key: ${e.message}")
                    }
                }
                
                // Create a new key
                val success = CryptoUtils.createSecretKey()
                if (!success) {
                    Timber.e("Failed to create encryption key")
                    return@launch
                }
                
                // Test the key
                val testValue = "test_encryption_${System.currentTimeMillis()}"
                val testEncrypt = CryptoUtils.encrypt(testValue)
                if (testEncrypt == null) {
                    Timber.e("Test encryption failed")
                    return@launch
                }
                
                val testDecrypt = CryptoUtils.decrypt(testEncrypt)
                if (testDecrypt != testValue) {
                    Timber.e("Test decryption failed: expected '$testValue', got '${testDecrypt ?: "null"}'")
                    return@launch
                }
                
                Timber.i("Encryption system successfully initialized!")
            } catch (e: Exception) {
                Timber.e(e, "Fatal error setting up encryption: ${e.message}")
            }
        }
        
        // Make sure crypto initialization completes before app starts
        runBlocking {
            cryptoJob.join()
            Timber.i("SafestWallet application initialized")
        }
    }
}