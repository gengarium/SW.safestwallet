package com.safestwallet.sw.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WalletDao {
    @Query("SELECT * FROM wallet_table ORDER BY timeCreated DESC")
    fun getAllWallets(): LiveData<List<WalletEntity>>
    
    @Query("SELECT * FROM wallet_table WHERE id = :walletId")
    fun getWalletById(walletId: String): LiveData<WalletEntity?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWallet(wallet: WalletEntity)
    
    @Update
    suspend fun updateWallet(wallet: WalletEntity)
    
    @Delete
    suspend fun deleteWallet(wallet: WalletEntity)
    
    @Query("SELECT COUNT(*) FROM wallet_table")
    suspend fun getWalletCount(): Int

    @Query("SELECT * FROM wallet_table WHERE id = :walletId")
suspend fun getWalletByIdSync(walletId: String): WalletEntity?
}