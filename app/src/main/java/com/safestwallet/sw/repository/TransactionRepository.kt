package com.safestwallet.sw.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.safestwallet.sw.blockchain.MultiSigManager
import com.safestwallet.sw.blockchain.OtpManager
import com.safestwallet.sw.blockchain.SolanaClient
import com.safestwallet.sw.blockchain.TransactionResult
import com.safestwallet.sw.data.AppDatabase
import com.safestwallet.sw.data.TransactionEntity
import com.safestwallet.sw.model.MultiSigWallet
import com.safestwallet.sw.model.TransactionStatus
import com.safestwallet.sw.model.WalletTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.math.BigDecimal
import java.util.*

/**
 * Repository per la gestione delle transazioni
 */
class TransactionRepository(
    private val database: AppDatabase,
    private val solanaClient: SolanaClient,
    private val multiSigManager: MultiSigManager,
    private val otpManager: OtpManager,
    private val whitelistManager: WhitelistManager
) {
    
    /**
     * Ottiene le transazioni per un dato indirizzo
     */
    fun getTransactionsForAddress(address: String): LiveData<List<WalletTransaction>> {
        return database.transactionDao().getTransactionsForAddress(address).map { entities ->
            entities.map { it.toWalletTransaction() }
        }
    }
    
    /**
     * Ottiene le transazioni recenti
     */
    fun getRecentTransactions(limit: Int = 10): LiveData<List<WalletTransaction>> {
        return database.transactionDao().getRecentTransactions(limit).map { entities ->
            entities.map { it.toWalletTransaction() }
        }
    }
    
    /**
     * Inizia una nuova transazione
     */
    suspend fun startTransaction(
        wallet: MultiSigWallet,
        destination: String,
        amount: Double
    ): TransactionStartResult = withContext(Dispatchers.IO) {
        try {
            // Verifica che l'indirizzo di destinazione sia nella whitelist
            if (!whitelistManager.isAddressWhitelisted(destination)) {
                return@withContext TransactionStartResult.Error("Destination address not in whitelist")
            }
            
            // Verifica che ci siano fondi sufficienti
            val balance = solanaClient.getBalance(wallet.publicKey)
            
            if (balance == null) {
                return@withContext TransactionStartResult.Error("Could not retrieve wallet balance")
            }
            
            val amountInSol = BigDecimal(amount)
            val fee = amountInSol.multiply(BigDecimal("0.02")) // 2% fee
            val totalAmount = amountInSol.add(fee)
            
            if (balance < totalAmount) {
                return@withContext TransactionStartResult.Error("Insufficient funds")
            }
            
            // Crea una transazione preliminare
            val lamports = (amount * LAMPORTS_PER_SOL).toLong()
            val transactionResult = multiSigManager.createTransaction(wallet, destination, lamports)
            
            if (transactionResult is TransactionResult.Error) {
                return@withContext TransactionStartResult.Error(transactionResult.message)
            }
            
            if (transactionResult is TransactionResult.TransactionCreated) {
                // Crea un record della transazione nel database
                val transaction = WalletTransaction(
                    id = UUID.randomUUID().toString(),
                    fromAddress = wallet.publicKey,
                    toAddress = destination,
                    amount = amount,
                    fee = fee.toDouble(),
                    status = TransactionStatus.PENDING,
                    timestamp = System.currentTimeMillis()
                )
                
                // Salva la transazione nel database
                database.transactionDao().insertTransaction(TransactionEntity.fromWalletTransaction(transaction))
                
                return@withContext TransactionStartResult.Success(transaction, transactionResult)
            }
            
            return@withContext TransactionStartResult.Error("Unexpected transaction state")
        } catch (e: Exception) {
            Timber.e(e, "Error starting transaction")
            return@withContext TransactionStartResult.Error("Error starting transaction: ${e.message}")
        }
    }
    
    /**
     * Completa una transazione con verifica OTP
     */
    suspend fun completeTransactionWithOtp(
        transactionId: String,
        wallet: MultiSigWallet,
        otp: String,
        transactionResult: TransactionResult.TransactionCreated
    ): TransactionCompleteResult = withContext(Dispatchers.IO) {
        try {
            // Verifica l'OTP
            val otpValid = otpManager.verifyOtp(wallet.encryptedOtpSeed, otp)
            
            if (!otpValid) {
                return@withContext TransactionCompleteResult.Error("Invalid OTP")
            }
            
            // Finalizza la transazione con la seconda firma
            val finalizedResult = multiSigManager.signAndFinalizeTransaction(transactionResult, true)
            
            if (finalizedResult is TransactionResult.Error) {
                // Aggiorna lo stato della transazione nel database
                database.transactionDao().updateTransactionStatus(
                    transactionId,
                    TransactionStatus.FAILED,
                    null,
                    null
                )
                return@withContext TransactionCompleteResult.Error(finalizedResult.message)
            }
            
            if (finalizedResult is TransactionResult.ReadyToSend) {
                // Invia la transazione alla blockchain
                val sendResult = multiSigManager.sendTransaction(solanaClient, finalizedResult)
                
                if (sendResult is TransactionResult.Error) {
                    // Aggiorna lo stato della transazione nel database
                    database.transactionDao().updateTransactionStatus(
                        transactionId,
                        TransactionStatus.FAILED,
                        null,
                        null
                    )
                    return@withContext TransactionCompleteResult.Error(sendResult.message)
                }
                
                if (sendResult is TransactionResult.Success) {
                    // Aggiorna lo stato della transazione nel database
                    database.transactionDao().updateTransactionStatus(
                        transactionId,
                        TransactionStatus.CONFIRMED,
                        sendResult.signature,
                        null // Blocco non ancora disponibile
                    )
                    return@withContext TransactionCompleteResult.Success(sendResult.signature)
                }
            }
            
            return@withContext TransactionCompleteResult.Error("Unexpected transaction state")
        } catch (e: Exception) {
            Timber.e(e, "Error completing transaction")
            
            // Aggiorna lo stato della transazione nel database
            database.transactionDao().updateTransactionStatus(
                transactionId,
                TransactionStatus.FAILED,
                null,
                null
            )
            
            return@withContext TransactionCompleteResult.Error("Error completing transaction: ${e.message}")
        }
    }
    
    companion object {
        private const val LAMPORTS_PER_SOL = 1_000_000_000L
    }
}

/**
 * Risultato dell'avvio di una transazione
 */
sealed class TransactionStartResult {
    data class Success(
        val transaction: WalletTransaction,
        val preparedTransaction: TransactionResult.TransactionCreated
    ) : TransactionStartResult()
    
    data class Error(val message: String) : TransactionStartResult()
}

/**
 * Risultato del completamento di una transazione
 */
sealed class TransactionCompleteResult {
    data class Success(val signature: String) : TransactionCompleteResult()
    data class Error(val message: String) : TransactionCompleteResult()
}