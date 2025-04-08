package com.safestwallet.sw.blockchain

import android.util.Base64
import com.safestwallet.sw.model.MultiSigWallet
import com.safestwallet.sw.util.CryptoUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.p2p.solanaj.core.Account
import org.p2p.solanaj.core.PublicKey
import org.p2p.solanaj.core.Transaction
import org.p2p.solanaj.programs.SystemProgram
import timber.log.Timber
import java.nio.ByteBuffer
import java.util.*

/**
 * Gestisce le operazioni multi-signature per il wallet
 */
class MultiSigManager {
    
    /**
     * Recupera le chiavi private dal wallet crittografato
     */
    suspend fun getKeysFromWallet(wallet: MultiSigWallet): Pair<Account, Account>? = withContext(Dispatchers.Default) {
        try {
            // Decifra le chiavi private
            val privateKey1Base64 = CryptoUtils.decrypt(wallet.encryptedPrivateKey1)
                ?: throw Exception("Failed to decrypt private key 1")
            
            val privateKey2Base64 = CryptoUtils.decrypt(wallet.encryptedPrivateKey2)
                ?: throw Exception("Failed to decrypt private key 2")
            
            // Converte da Base64 a ByteArray
            val privateKey1Bytes = Base64.decode(privateKey1Base64, Base64.NO_WRAP)
            val privateKey2Bytes = Base64.decode(privateKey2Base64, Base64.NO_WRAP)
            
            // Crea gli account
            val account1 = Account(privateKey1Bytes)
            val account2 = Account(privateKey2Bytes)
            
            return@withContext Pair(account1, account2)
        } catch (e: Exception) {
            Timber.e(e, "Error getting keys from wallet")
            return@withContext null
        }
    }
    
    /**
     * Crea una transazione multi-signature
     */
    suspend fun createTransaction(
        wallet: MultiSigWallet,
        destination: String,
        lamports: Long,
        includeCommission: Boolean = true
    ): TransactionResult = withContext(Dispatchers.Default) {
        try {
            // Recupera le chiavi
            val keyPair = getKeysFromWallet(wallet)
                ?: return@withContext TransactionResult.Error("Failed to retrieve keys")
            
            val (account1, account2) = keyPair
            
            // Verifica che la prima chiave corrisponda all'indirizzo del wallet
            if (account1.publicKey.toString() != wallet.publicKey) {
                return@withContext TransactionResult.Error("Key mismatch with wallet address")
            }
            
            // Calcola la commissione (2%)
            val commissionAmount = if (includeCommission) (lamports * 0.02).toLong() else 0L
            val totalAmount = lamports + commissionAmount
            
            // Crea la transazione base
            val transaction = Transaction()
            
            // Aggiungi istruzione di trasferimento
            transaction.addInstruction(
                SystemProgram.transfer(
                    account1.publicKey,
                    PublicKey(destination),
                    lamports
                )
            )
            
            // Se necessario, aggiungi istruzione per la commissione
            if (commissionAmount > 0) {
                // Indirizzo del tuo wallet per ricevere la commissione
                val feeAddress = PublicKey("YOUR_COMMISSION_WALLET_ADDRESS")
                
                transaction.addInstruction(
                    SystemProgram.transfer(
                        account1.publicKey,
                        feeAddress,
                        commissionAmount
                    )
                )
            }
            
            // Firma con la prima chiave
            transaction.sign(account1)
            
            // Estrai i dati della transazione per la successiva firma
            val transactionData = transaction.serialize()
            
            return@withContext TransactionResult.TransactionCreated(
                transaction = transaction,
                transactionData = transactionData,
                account1 = account1,
                account2 = account2,
                totalAmount = totalAmount,
                commissionAmount = commissionAmount
            )
        } catch (e: Exception) {
            Timber.e(e, "Error creating multi-sig transaction: ${e.message}")
            return@withContext TransactionResult.Error("Error creating transaction: ${e.message}")
        }
    }
    
    /**
     * Completa la firma multi-signature e finalizza la transazione
     */
    suspend fun signAndFinalizeTransaction(
        transactionResult: TransactionResult.TransactionCreated,
        otpVerified: Boolean
    ): TransactionResult = withContext(Dispatchers.Default) {
        try {
            // Verifica che l'OTP sia stato verificato
            if (!otpVerified) {
                return@withContext TransactionResult.Error("OTP verification required")
            }
            
            val transaction = transactionResult.transaction
            
            // Firma con la seconda chiave
            transaction.sign(transactionResult.account2)
            
            // A questo punto la transazione è firmata da entrambe le chiavi
            // e può essere trasmessa alla blockchain
            
            // In un'implementazione reale, qui invieresti la transazione alla rete Solana
            val serializedTransaction = transaction.serialize()
            
            return@withContext TransactionResult.ReadyToSend(
                transaction = transaction,
                serializedData = serializedTransaction,
                signatures = listOf(
                    transactionResult.account1.publicKey.toString(),
                    transactionResult.account2.publicKey.toString()
                )
            )
        } catch (e: Exception) {
            Timber.e(e, "Error finalizing multi-sig transaction: ${e.message}")
            return@withContext TransactionResult.Error("Error finalizing transaction: ${e.message}")
        }
    }
    
    /**
     * Trasmette una transazione finalizzata alla blockchain
     */
    suspend fun sendTransaction(
        solanaClient: SolanaClient,
        transactionResult: TransactionResult.ReadyToSend
    ): TransactionResult = withContext(Dispatchers.IO) {
        try {
            // Invia la transazione alla blockchain
            val signature = solanaClient.sendTransaction(transactionResult.serializedData)
            
            if (signature != null) {
                return@withContext TransactionResult.Success(
                    signature = signature,
                    transaction = transactionResult.transaction
                )
            } else {
                return@withContext TransactionResult.Error("Failed to send transaction")
            }
        } catch (e: Exception) {
            Timber.e(e, "Error sending transaction: ${e.message}")
            return@withContext TransactionResult.Error("Error sending transaction: ${e.message}")
        }
    }
}

/**
 * Stati possibili per una transazione multi-signature
 */
sealed class TransactionResult {
    /**
     * Transazione creata e firmata dalla prima chiave
     */
    data class TransactionCreated(
        val transaction: Transaction,
        val transactionData: ByteArray,
        val account1: Account,
        val account2: Account,
        val totalAmount: Long,
        val commissionAmount: Long
    ) : TransactionResult()
    
    /**
     * Transazione firmata da entrambe le chiavi e pronta per l'invio
     */
    data class ReadyToSend(
        val transaction: Transaction,
        val serializedData: ByteArray,
        val signatures: List<String>
    ) : TransactionResult()
    
    /**
     * Transazione inviata con successo
     */
    data class Success(
        val signature: String,
        val transaction: Transaction
    ) : TransactionResult()
    
    /**
     * Errore durante il processo di transazione
     */
    data class Error(val message: String) : TransactionResult()
}