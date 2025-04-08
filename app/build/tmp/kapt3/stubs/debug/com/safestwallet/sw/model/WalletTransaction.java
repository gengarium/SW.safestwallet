package com.safestwallet.sw.model;

import java.lang.System;

/**
 * Rappresenta una transazione wallet
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0007H\u00c6\u0003J\t\u0010#\u001a\u00020\u0007H\u00c6\u0003J\t\u0010$\u001a\u00020\nH\u00c6\u0003J\t\u0010%\u001a\u00020\fH\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\'\u001a\u0004\u0018\u00010\fH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013Jl\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\fH\u00c6\u0001\u00a2\u0006\u0002\u0010)J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u00d6\u0003J\t\u0010.\u001a\u00020/H\u00d6\u0001J\t\u00100\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\f\u00a2\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017\u00a8\u00061"}, d2 = {"Lcom/safestwallet/sw/model/WalletTransaction;", "Ljava/io/Serializable;", "id", "", "fromAddress", "toAddress", "amount", "", "fee", "status", "Lcom/safestwallet/sw/model/TransactionStatus;", "timestamp", "", "signature", "blockHeight", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDLcom/safestwallet/sw/model/TransactionStatus;JLjava/lang/String;Ljava/lang/Long;)V", "getAmount", "()D", "getBlockHeight", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getFee", "getFromAddress", "()Ljava/lang/String;", "getId", "getSignature", "getStatus", "()Lcom/safestwallet/sw/model/TransactionStatus;", "getTimestamp", "()J", "getToAddress", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDLcom/safestwallet/sw/model/TransactionStatus;JLjava/lang/String;Ljava/lang/Long;)Lcom/safestwallet/sw/model/WalletTransaction;", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
public final class WalletTransaction implements java.io.Serializable {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fromAddress = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String toAddress = null;
    private final double amount = 0.0;
    private final double fee = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final com.safestwallet.sw.model.TransactionStatus status = null;
    private final long timestamp = 0L;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String signature = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long blockHeight = null;
    
    /**
     * Rappresenta una transazione wallet
     */
    @org.jetbrains.annotations.NotNull()
    public final com.safestwallet.sw.model.WalletTransaction copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String fromAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String toAddress, double amount, double fee, @org.jetbrains.annotations.NotNull()
    com.safestwallet.sw.model.TransactionStatus status, long timestamp, @org.jetbrains.annotations.Nullable()
    java.lang.String signature, @org.jetbrains.annotations.Nullable()
    java.lang.Long blockHeight) {
        return null;
    }
    
    /**
     * Rappresenta una transazione wallet
     */
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    /**
     * Rappresenta una transazione wallet
     */
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    /**
     * Rappresenta una transazione wallet
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public WalletTransaction(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String fromAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String toAddress, double amount, double fee, @org.jetbrains.annotations.NotNull()
    com.safestwallet.sw.model.TransactionStatus status, long timestamp, @org.jetbrains.annotations.Nullable()
    java.lang.String signature, @org.jetbrains.annotations.Nullable()
    java.lang.Long blockHeight) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFromAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getToAddress() {
        return null;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    public final double getAmount() {
        return 0.0;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    public final double getFee() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.safestwallet.sw.model.TransactionStatus component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.safestwallet.sw.model.TransactionStatus getStatus() {
        return null;
    }
    
    public final long component7() {
        return 0L;
    }
    
    public final long getTimestamp() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSignature() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getBlockHeight() {
        return null;
    }
}