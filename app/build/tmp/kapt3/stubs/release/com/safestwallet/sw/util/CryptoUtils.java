package com.safestwallet.sw.util;

import java.lang.System;

/**
 * Utility per operazioni crittografiche
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\u0004J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000f\u001a\u00020\u0004J\u0006\u0010\u0010\u001a\u00020\u0004J\u0006\u0010\u0011\u001a\u00020\u0004J\u000e\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004J\u0006\u0010\u0014\u001a\u00020\u000bJ\u000e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/safestwallet/sw/util/CryptoUtils;", "", "()V", "AES_MODE", "", "ANDROID_KEYSTORE", "IV_LENGTH", "", "KEY_ALIAS", "TAG_LENGTH", "createSecretKey", "", "decrypt", "encryptedText", "encrypt", "plainText", "generateMnemonic", "generateOtp", "generateOtpFromSeed", "seed", "keyExists", "sha256", "input", "app_release"})
public final class CryptoUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.safestwallet.sw.util.CryptoUtils INSTANCE = null;
    private static final java.lang.String ANDROID_KEYSTORE = "AndroidKeyStore";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_ALIAS = "SAFEST_WALLET_KEY";
    private static final java.lang.String AES_MODE = "AES/GCM/NoPadding";
    private static final int IV_LENGTH = 12;
    private static final int TAG_LENGTH = 128;
    
    private CryptoUtils() {
        super();
    }
    
    /**
     * Genera una frase mnemonica casuale (seed phrase)
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String generateMnemonic() {
        return null;
    }
    
    /**
     * Crea una chiave sicura nel keystore Android
     */
    public final boolean createSecretKey() {
        return false;
    }
    
    /**
     * Cifra dati utilizzando la chiave nel keystore
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String encrypt(@org.jetbrains.annotations.NotNull()
    java.lang.String plainText) {
        return null;
    }
    
    /**
     * Decifra dati utilizzando la chiave nel keystore
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String decrypt(@org.jetbrains.annotations.NotNull()
    java.lang.String encryptedText) {
        return null;
    }
    
    /**
     * Genera un codice OTP casuale di 6 cifre
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String generateOtp() {
        return null;
    }
    
    /**
     * Calcola hash SHA-256 di una stringa
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String sha256(@org.jetbrains.annotations.NotNull()
    java.lang.String input) {
        return null;
    }
    
    /**
     * Verifica se la chiave esiste nel keystore
     */
    public final boolean keyExists() {
        return false;
    }
    
    /**
     * Genera un OTP usando il seed
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String generateOtpFromSeed(@org.jetbrains.annotations.NotNull()
    java.lang.String seed) {
        return null;
    }
}