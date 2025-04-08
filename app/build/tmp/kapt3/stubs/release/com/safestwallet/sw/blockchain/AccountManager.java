package com.safestwallet.sw.blockchain;

import java.lang.System;

/**
 * Gestisce la creazione e l'importazione di wallet Solana
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0006H\u0002J#\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lcom/safestwallet/sw/blockchain/AccountManager;", "", "()V", "createMultiSigWallet", "Lcom/safestwallet/sw/model/MultiSigWallet;", "name", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateOtpSeed", "importWalletFromSeed", "seedPhrase", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isValidPublicKey", "", "publicKey", "Companion", "app_release"})
public final class AccountManager {
    @org.jetbrains.annotations.NotNull()
    public static final com.safestwallet.sw.blockchain.AccountManager.Companion Companion = null;
    
    public AccountManager() {
        super();
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
     * Importa un wallet multi-signature esistente
     * @param seedPhrase Frase mnemonica
     * @param name Nome del wallet
     * @return Il wallet importato o null in caso di errore
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object importWalletFromSeed(@org.jetbrains.annotations.NotNull()
    java.lang.String seedPhrase, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.safestwallet.sw.model.MultiSigWallet> continuation) {
        return null;
    }
    
    /**
     * Genera un seed per OTP
     */
    private final java.lang.String generateOtpSeed() {
        return null;
    }
    
    /**
     * Verifica se una chiave pubblica è valida
     */
    public final boolean isValidPublicKey(@org.jetbrains.annotations.NotNull()
    java.lang.String publicKey) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u00a8\u0006\u0006"}, d2 = {"Lcom/safestwallet/sw/blockchain/AccountManager$Companion;", "", "()V", "formatShortAddress", "", "address", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Ottiene il formato breve di un indirizzo (per visualizzazione)
         */
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String formatShortAddress(@org.jetbrains.annotations.NotNull()
        java.lang.String address) {
            return null;
        }
    }
}