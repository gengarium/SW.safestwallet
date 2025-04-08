package com.safestwallet.sw.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.safestwallet.sw.blockchain.AccountManager
import com.safestwallet.sw.blockchain.SolanaClient
import com.safestwallet.sw.data.AppDatabase
import com.safestwallet.sw.data.WalletEntity
import com.safestwallet.sw.model.MultiSigWallet
import com.safestwallet.sw.model.SafeWallet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.math.BigDecimal

/**
 * Repository per la gestione dei wallet
 */
class WalletRepository(
    private val database: AppDatabase,
    private val solanaClient: SolanaClient = SolanaClient(),
    private val accountManager: AccountManager = AccountManager()
) {
    
    /**
     * Ottiene tutti i wallet (versione sicura senza dati sensibili)
     */
    fun getAllWallets(): LiveData<List<SafeWallet>> {
        return database.walletDao().getAllWallets().map { walletEntities ->
            walletEntities.map { it.toSafeWallet() }
        }
    }
    
    /**
     * Ottiene un wallet tramite ID
     */
    fun getWalletById(walletId: String): LiveData<MultiSigWallet?> {
        return database.walletDao().getWalletById(walletId).map { walletEntity ->
            walletEntity?.toMultiSigWallet()
        }
    }
    
    /**
     * Crea un nuovo wallet multi-signature
     * @param name Nome del wallet
     * @return Il nuovo wallet o null in caso di errore
     */
    suspend fun createMultiSigWallet(name: String): MultiSigWallet? = withContext(Dispatchers.IO) {
        try {
            val wallet = accountManager.createMultiSigWallet(name)
            
            if (wallet != null) {
                // Salva il wallet nel database
                database.walletDao().insertWallet(WalletEntity.fromMultiSigWallet(wallet))
                Timber.i("Wallet saved to database: ${wallet.id}")
            }
            
            return@withContext wallet
        } catch (e: Exception) {
            Timber.e(e, "Error creating and saving wallet")
            return@withContext null
        }
    }
    
    /**
     * Importa un wallet da una frase mnemonica
     */
    suspend fun importWalletFromSeed(seedPhrase: String, name: String): MultiSigWallet? = withContext(Dispatchers.IO) {
        try {
            val wallet = accountManager.importWalletFromSeed(seedPhrase, name)
            
            if (wallet != null) {
                // Salva il wallet nel database
                database.walletDao().insertWallet(WalletEntity.fromMultiSigWallet(wallet))
                Timber.i("Imported wallet saved to database: ${wallet.id}")
            }
            
            return@withContext wallet
        } catch (e: Exception) {
            Timber.e(e, "Error importing and saving wallet")
            return@withContext null
        }
    }
    
    /**
     * Elimina un wallet
     */
    suspend fun deleteWallet(walletId: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val walletEntity = database.walletDao().getWalletByIdSync(walletId)
            
            if (walletEntity != null) {
                database.walletDao().deleteWallet(walletEntity)
                Timber.i("Wallet deleted: $walletId")
                return@withContext true
            }
            
            return@withContext false
        } catch (e: Exception) {
            Timber.e(e, "Error deleting wallet")
            return@withContext false
        }
    }
    
    /**
     * Ottiene il saldo di un wallet
     */
    suspend fun getWalletBalance(publicKey: String): BigDecimal? = withContext(Dispatchers.IO) {
        return@withContext solanaClient.getBalance(publicKey)
    }
    
    /**
     * Controlla se un indirizzo è valido
     */
    fun isValidAddress(address: String): Boolean {
        return accountManager.isValidPublicKey(address)
    }
    
    /**
     * Conta quanti wallet sono presenti nel database
     */
    suspend fun getWalletCount(): Int = withContext(Dispatchers.IO) {
        return@withContext database.walletDao().getWalletCount()
    }
}