package com.safestwallet.sw.model

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