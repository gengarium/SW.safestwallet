package com.safestwallet.sw.model;

import java.lang.System;

/**
 * Rappresenta un wallet multi-signature per Solana
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u000bH\u00c6\u0003JY\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u00d6\u0003J\t\u0010$\u001a\u00020%H\u00d6\u0001J\u0006\u0010&\u001a\u00020!J\u0006\u0010\'\u001a\u00020(J\t\u0010)\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006*"}, d2 = {"Lcom/safestwallet/sw/model/MultiSigWallet;", "Ljava/io/Serializable;", "id", "", "name", "publicKey", "encryptedPrivateKey1", "encryptedPrivateKey2", "encryptedSeedPhrase", "encryptedOtpSeed", "timeCreated", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getEncryptedOtpSeed", "()Ljava/lang/String;", "getEncryptedPrivateKey1", "getEncryptedPrivateKey2", "getEncryptedSeedPhrase", "getId", "getName", "getPublicKey", "getTimeCreated", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "", "hashCode", "", "isFullyConfigured", "toSafeWallet", "Lcom/safestwallet/sw/model/SafeWallet;", "toString", "app_debug"})
public final class MultiSigWallet implements java.io.Serializable {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String publicKey = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String encryptedPrivateKey1 = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String encryptedPrivateKey2 = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String encryptedSeedPhrase = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String encryptedOtpSeed = null;
    private final long timeCreated = 0L;
    
    /**
     * Rappresenta un wallet multi-signature per Solana
     */
    @org.jetbrains.annotations.NotNull()
    public final com.safestwallet.sw.model.MultiSigWallet copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String publicKey, @org.jetbrains.annotations.NotNull()
    java.lang.String encryptedPrivateKey1, @org.jetbrains.annotations.NotNull()
    java.lang.String encryptedPrivateKey2, @org.jetbrains.annotations.NotNull()
    java.lang.String encryptedSeedPhrase, @org.jetbrains.annotations.NotNull()
    java.lang.String encryptedOtpSeed, long timeCreated) {
        return null;
    }
    
    /**
     * Rappresenta un wallet multi-signature per Solana
     */
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    /**
     * Rappresenta un wallet multi-signature per Solana
     */
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    /**
     * Rappresenta un wallet multi-signature per Solana
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public MultiSigWallet(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String publicKey, @org.jetbrains.annotations.NotNull()
    java.lang.String encryptedPrivateKey1, @org.jetbrains.annotations.NotNull()
    java.lang.String encryptedPrivateKey2, @org.jetbrains.annotations.NotNull()
    java.lang.String encryptedSeedPhrase, @org.jetbrains.annotations.NotNull()
    java.lang.String encryptedOtpSeed, long timeCreated) {
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
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPublicKey() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEncryptedPrivateKey1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEncryptedPrivateKey2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEncryptedSeedPhrase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEncryptedOtpSeed() {
        return null;
    }
    
    public final long component8() {
        return 0L;
    }
    
    public final long getTimeCreated() {
        return 0L;
    }
    
    /**
     * Controlla se il wallet è completamente configurato
     */
    public final boolean isFullyConfigured() {
        return false;
    }
    
    /**
     * Crea una versione sicura del wallet con i dati sensibili rimossi,
     * adatta per essere visualizzata o memorizzata in modo non sicuro
     */
    @org.jetbrains.annotations.NotNull()
    public final com.safestwallet.sw.model.SafeWallet toSafeWallet() {
        return null;
    }
}