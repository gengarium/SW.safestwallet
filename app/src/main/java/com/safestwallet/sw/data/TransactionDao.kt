package com.safestwallet.sw.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.safestwallet.sw.model.TransactionStatus

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transaction_table WHERE fromAddress = :address OR toAddress = :address ORDER BY timestamp DESC")
    fun getTransactionsForAddress(address: String): LiveData<List<TransactionEntity>>
    
    @Query("SELECT * FROM transaction_table ORDER BY timestamp DESC LIMIT :limit")
    fun getRecentTransactions(limit: Int): LiveData<List<TransactionEntity>>
    
    @Query("SELECT * FROM transaction_table WHERE id = :transactionId")
    fun getTransactionById(transactionId: String): LiveData<TransactionEntity?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)
    
    @Update
    suspend fun updateTransaction(transaction: TransactionEntity)
    
    @Query("UPDATE transaction_table SET status = :status, signature = :signature, blockHeight = :blockHeight WHERE id = :transactionId")
    suspend fun updateTransactionStatus(transactionId: String, status: TransactionStatus, signature: String?, blockHeight: Long?)
}