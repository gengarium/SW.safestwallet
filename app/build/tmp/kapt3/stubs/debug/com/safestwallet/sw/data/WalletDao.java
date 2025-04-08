package com.safestwallet.sw.data;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\b2\u0006\u0010\u000b\u001a\u00020\fH\'J\u001b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0011\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lcom/safestwallet/sw/data/WalletDao;", "", "deleteWallet", "", "wallet", "Lcom/safestwallet/sw/data/WalletEntity;", "(Lcom/safestwallet/sw/data/WalletEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWallets", "Landroidx/lifecycle/LiveData;", "", "getWalletById", "walletId", "", "getWalletByIdSync", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWalletCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertWallet", "updateWallet", "app_debug"})
public abstract interface WalletDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM wallet_table ORDER BY timeCreated DESC")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.safestwallet.sw.data.WalletEntity>> getAllWallets();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM wallet_table WHERE id = :walletId")
    public abstract androidx.lifecycle.LiveData<com.safestwallet.sw.data.WalletEntity> getWalletById(@org.jetbrains.annotations.NotNull()
    java.lang.String walletId);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert(onConflict = 1)
    public abstract java.lang.Object insertWallet(@org.jetbrains.annotations.NotNull()
    com.safestwallet.sw.data.WalletEntity wallet, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Update()
    public abstract java.lang.Object updateWallet(@org.jetbrains.annotations.NotNull()
    com.safestwallet.sw.data.WalletEntity wallet, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Delete()
    public abstract java.lang.Object deleteWallet(@org.jetbrains.annotations.NotNull()
    com.safestwallet.sw.data.WalletEntity wallet, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "SELECT COUNT(*) FROM wallet_table")
    public abstract java.lang.Object getWalletCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "SELECT * FROM wallet_table WHERE id = :walletId")
    public abstract java.lang.Object getWalletByIdSync(@org.jetbrains.annotations.NotNull()
    java.lang.String walletId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.safestwallet.sw.data.WalletEntity> continuation);
}