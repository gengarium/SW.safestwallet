package com.safestwallet.sw.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.safestwallet.sw.model.TransactionStatus
import com.safestwallet.sw.model.WalletTransaction

/**
 * Entità Room per memorizzare le transazioni nel database locale
 */
@Entity(tableName = "transaction_table")
data class TransactionEntity(
    @PrimaryKey
    val id: String,
    val fromAddress: String,
    val toAddress: String,
    val amount: Double,
    val fee: Double,
    val status: TransactionStatus,
    val timestamp: Long,
    val signature: String?,
    val blockHeight: Long?
) {
    /**
     * Converte l'entità in un oggetto del modello
     */
    fun toWalletTransaction(): WalletTransaction {
        return WalletTransaction(
            id = id,
            fromAddress = fromAddress,
            toAddress = toAddress,
            amount = amount,
            fee = fee,
            status = status,
            timestamp = timestamp,
            signature = signature,
            blockHeight = blockHeight
        )
    }
    
    companion object {
        /**
         * Crea un'entità da un oggetto del modello
         */
        fun fromWalletTransaction(transaction: WalletTransaction): TransactionEntity {
            return TransactionEntity(
                id = transaction.id,
                fromAddress = transaction.fromAddress,
                toAddress = transaction.toAddress,
                amount = transaction.amount,
                fee = transaction.fee,
                status = transaction.status,
                timestamp = transaction.timestamp,
                signature = transaction.signature,
                blockHeight = transaction.blockHeight
            )
        }
    }
}