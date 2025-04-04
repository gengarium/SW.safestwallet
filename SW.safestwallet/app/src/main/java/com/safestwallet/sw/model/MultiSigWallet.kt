package com.safestwallet.sw.model

import com.safestwallet.sw.util.CryptoUtils
import java.security.KeyPair

class MultiSigWallet(
    val privateKey1: ByteArray,
    val privateKey2: ByteArray
) {
    val publicKey: String
    val address: String
    
    init {
        // Deriva la chiave pubblica combinata dalle due chiavi private
        publicKey = CryptoUtils.derivePublicKeyCombined(privateKey1, privateKey2)
        address = CryptoUtils.addressFromPublicKey(publicKey)
    }
    
    fun sign(data: ByteArray): SignedTransaction {
        val signature1 = CryptoUtils.sign(data, privateKey1)
        val signature2 = CryptoUtils.sign(data, privateKey2)
        
        return SignedTransaction(
            transaction = Transaction.fromData(data),
            signature1 = signature1,
            signature2 = signature2
        )
    }
    
    fun getBackupPhrase1(): String = CryptoUtils.seedPhraseFromPrivateKey(privateKey1)
    fun getBackupPhrase2(): String = CryptoUtils.seedPhraseFromPrivateKey(privateKey2)
}

data class Transaction(
    val sender: String,
    val recipient: String,
    val amount: Double,
    val timestamp: Long,
    val memo: String? = null
) {
    val data: ByteArray get() {
        // Crea una rappresentazione binaria della transazione
        val builder = StringBuilder()
        builder.append(sender)
        builder.append(recipient)
        builder.append(amount.toString())
        builder.append(timestamp.toString())
        memo?.let { builder.append(it) }
        return builder.toString().toByteArray()
    }
    
    companion object {
        fun fromData(data: ByteArray): Transaction {
            // Implementazione per ricostruire la transazione dai dati
            // Semplificata per ora
            return Transaction("dummy", "dummy", 0.0, 0)
        }
    }
}

data class SignedTransaction(
    val transaction: Transaction,
    val signature1: ByteArray,
    val signature2: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        
        other as SignedTransaction
        
        if (transaction != other.transaction) return false
        if (!signature1.contentEquals(other.signature1)) return false
        if (!signature2.contentEquals(other.signature2)) return false
        
        return true
    }
    
    override fun hashCode(): Int {
        var result = transaction.hashCode()
        result = 31 * result + signature1.contentHashCode()
        result = 31 * result + signature2.contentHashCode()
        return result
    }
}
