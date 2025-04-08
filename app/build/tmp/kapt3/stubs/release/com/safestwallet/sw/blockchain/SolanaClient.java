package com.safestwallet.sw.blockchain;

import java.lang.System;

/**
 * Client per interagire con la blockchain Solana
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u001b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\u0003H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/safestwallet/sw/blockchain/SolanaClient;", "", "endpoint", "", "(Ljava/lang/String;)V", "client", "accountExists", "", "publicKey", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBalance", "Ljava/math/BigDecimal;", "getVersion", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_release"})
public final class SolanaClient {
    private final java.lang.String client = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.safestwallet.sw.blockchain.SolanaClient.Companion Companion = null;
    private static final long LAMPORTS_PER_SOL = 1000000000L;
    
    public SolanaClient() {
        super();
    }
    
    public SolanaClient(@org.jetbrains.annotations.NotNull()
    java.lang.String endpoint) {
        super();
    }
    
    /**
     * Ottiene il saldo di un account Solana
     * @param publicKey Chiave pubblica dell'account
     * @return Saldo in SOL o null in caso di errore
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getBalance(@org.jetbrains.annotations.NotNull()
    java.lang.String publicKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.math.BigDecimal> continuation) {
        return null;
    }
    
    /**
     * Controlla se un account esiste sulla blockchain
     * @param publicKey Chiave pubblica dell'account
     * @return true se l'account esiste, false altrimenti
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object accountExists(@org.jetbrains.annotations.NotNull()
    java.lang.String publicKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    /**
     * Ottiene la versione del cluster Solana
     * @return Versione del cluster o null in caso di errore
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getVersion(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/safestwallet/sw/blockchain/SolanaClient$Companion;", "", "()V", "LAMPORTS_PER_SOL", "", "getClusterUrl", "", "isDevnet", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Ottiene l'URL del cluster in base all'ambiente
         */
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getClusterUrl(boolean isDevnet) {
            return null;
        }
    }
}