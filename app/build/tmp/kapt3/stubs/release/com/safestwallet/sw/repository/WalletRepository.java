package com.safestwallet.sw.repository;

import java.lang.System;

/**
 * Repository per la gestione dei wallet
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0012J\u001b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00122\u0006\u0010\u0010\u001a\u00020\fJ\u0011\u0010\u0019\u001a\u00020\u001aH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bJ#\u0010\u001c\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eJ\u000e\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lcom/safestwallet/sw/repository/WalletRepository;", "", "database", "Lcom/safestwallet/sw/data/AppDatabase;", "solanaClient", "Lcom/safestwallet/sw/blockchain/SolanaClient;", "accountManager", "Lcom/safestwallet/sw/blockchain/AccountManager;", "(Lcom/safestwallet/sw/data/AppDatabase;Lcom/safestwallet/sw/blockchain/SolanaClient;Lcom/safestwallet/sw/blockchain/AccountManager;)V", "createMultiSigWallet", "Lcom/safestwallet/sw/model/MultiSigWallet;", "name", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteWallet", "", "walletId", "getAllWallets", "Landroidx/lifecycle/LiveData;", "", "Lcom/safestwallet/sw/model/SafeWallet;", "getWalletBalance", "Ljava/math/BigDecimal;", "publicKey", "getWalletById", "getWalletCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "importWalletFromSeed", "seedPhrase", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isValidAddress", "address", "app_release"})
public final class WalletRepository {
    private final com.safestwallet.sw.data.AppDatabase database = null;
    private final com.safestwallet.sw.blockchain.SolanaClient solanaClient = null;
    private final com.safestwallet.sw.blockchain.AccountManager accountManager = null;
    
    public WalletRepository(@org.jetbrains.annotations.NotNull()
    com.safestwallet.sw.data.AppDatabase database, @org.jetbrains.annotations.NotNull()
    com.safestwallet.sw.blockchain.SolanaClient solanaClient, @org.jetbrains.annotations.NotNull()
    com.safestwallet.sw.blockchain.AccountManager accountManager) {
        super();
    }
    
    /**
     * Ottiene tutti i wallet (versione sicura senza dati sensibili)
     */
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.safestwallet.sw.model.SafeWallet>> getAllWallets() {
        return null;
    }
    
    /**
     * Ottiene un wallet tramite ID
     */
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.safestwallet.sw.model.MultiSigWallet> getWalletById(@org.jetbrains.annotations.NotNull()
    java.lang.String walletId) {
        return null;
    }
    
    /**
     * Crea un nuovo wallet multi-signature
     * @param name Nome del wallet
     * @return Il nuovo wallet o null in caso di errore
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createMultiSigWallet(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.safestwallet.sw.model.MultiSigWallet> continuation) {
        return null;
    }
    
    /**
     * Importa un wallet da una frase mnemonica
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object importWalletFromSeed(@org.jetbrains.annotations.NotNull()
    java.lang.String seedPhrase, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.safestwallet.sw.model.MultiSigWallet> continuation) {
        return null;
    }
    
    /**
     * Elimina un wallet
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteWallet(@org.jetbrains.annotations.NotNull()
    java.lang.String walletId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    /**
     * Ottiene il saldo di un wallet
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getWalletBalance(@org.jetbrains.annotations.NotNull()
    java.lang.String publicKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.math.BigDecimal> continuation) {
        return null;
    }
    
    /**
     * Controlla se un indirizzo è valido
     */
    public final boolean isValidAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String address) {
        return false;
    }
    
    /**
     * Conta quanti wallet sono presenti nel database
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getWalletCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation) {
        return null;
    }
}