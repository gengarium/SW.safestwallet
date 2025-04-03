package com.safestwallet.sw.model

data class Transaction(
    val sender: String,
    val recipient: String,
    val amount: Double,
    val timestamp: Long,
    val memo: String? = null,
    val data: ByteArray = createTransactionData(sender, recipient, amount, timestamp, memo)
) {
    companion object {
        private fun createTransactionData(
            sender: String,
            recipient: String,
            amount: Double,
            timestamp: Long,
            memo: String?
        ): ByteArray {
            // Create a byte representation of the transaction
            val dataString = "$sender|$recipient|$amount|$timestamp|${memo ?: ""}"
            return dataString.toByteArray()
        }
    }
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        
        other as Transaction
        
        if (sender != other.sender) return false
        if (recipient != other.recipient) return false
        if (amount != other.amount) return false
        if (timestamp != other.timestamp) return false
        if (memo != other.memo) return false
        if (!data.contentEquals(other.data)) return false
        
        return true
    }
    
    override fun hashCode(): Int {
        var result = sender.hashCode()
        result = 31 * result + recipient.hashCode()
        result = 31 * result + amount.hashCode()
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + (memo?.hashCode() ?: 0)
        result = 31 * result + data.contentHashCode()
        return result
    }
}