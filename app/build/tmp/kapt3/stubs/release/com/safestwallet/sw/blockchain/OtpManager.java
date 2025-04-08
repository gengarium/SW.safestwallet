package com.safestwallet.sw.blockchain;

import java.lang.System;

/**
 * Gestisce la generazione e verifica dei codici OTP
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J!\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ!\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lcom/safestwallet/sw/blockchain/OtpManager;", "", "()V", "generateOtp", "", "encryptedOtpSeed", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerOtpWithSwitchboard", "", "publicKey", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyOtp", "otp", "app_release"})
public final class OtpManager {
    
    public OtpManager() {
        super();
    }
    
    /**
     * Genera un codice OTP usando il seed fornito
     * @param encryptedOtpSeed Il seed OTP cifrato
     * @return Il codice OTP generato o null in caso di errore
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object generateOtp(@org.jetbrains.annotations.NotNull()
    java.lang.String encryptedOtpSeed, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> continuation) {
        return null;
    }
    
    /**
     * Verifica se un codice OTP è valido
     * @param encryptedOtpSeed Il seed OTP cifrato
     * @param otp Il codice OTP da verificare
     * @return true se l'OTP è valido, false altrimenti
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object verifyOtp(@org.jetbrains.annotations.NotNull()
    java.lang.String encryptedOtpSeed, @org.jetbrains.annotations.NotNull()
    java.lang.String otp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    /**
     * In un'implementazione reale, questo metodo invierebbe il seed OTP all'oracle Switchboard
     * Per ora, è solo un placeholder
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object registerOtpWithSwitchboard(@org.jetbrains.annotations.NotNull()
    java.lang.String publicKey, @org.jetbrains.annotations.NotNull()
    java.lang.String encryptedOtpSeed, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
}