package com.safestwallet.sw.repository;

import java.lang.System;

/**
 * Gestisce la whitelist degli indirizzi approvati
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011J\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u0014J\u000e\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u0014J\u000e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0018\u001a\u00020\u0019H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\r\u00a8\u0006\u001b"}, d2 = {"Lcom/safestwallet/sw/repository/WhitelistManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "gson", "Lcom/google/gson/Gson;", "whitelist", "", "Lcom/safestwallet/sw/model/WhitelistEntry;", "whitelistFile", "Ljava/io/File;", "getWhitelistFile", "()Ljava/io/File;", "addAddress", "", "address", "", "name", "getAllAddresses", "", "isAddressWhitelisted", "loadWhitelist", "removeAddress", "saveWhitelist", "", "Companion", "app_release"})
public final class WhitelistManager {
    private final android.content.Context context = null;
    private final com.google.gson.Gson gson = null;
    private java.util.List<com.safestwallet.sw.model.WhitelistEntry> whitelist;
    @org.jetbrains.annotations.NotNull()
    public static final com.safestwallet.sw.repository.WhitelistManager.Companion Companion = null;
    private static final java.lang.String WHITELIST_FILENAME = "whitelist.encrypted";
    
    public WhitelistManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final java.io.File getWhitelistFile() {
        return null;
    }
    
    /**
     * Carica la whitelist dal file crittografato
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.safestwallet.sw.model.WhitelistEntry> loadWhitelist() {
        return null;
    }
    
    /**
     * Salva la whitelist in un file crittografato
     */
    private final void saveWhitelist() {
    }
    
    /**
     * Aggiunge un indirizzo alla whitelist
     * @return true se l'indirizzo è stato aggiunto, false se era già presente
     */
    public final boolean addAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String name) {
        return false;
    }
    
    /**
     * Rimuove un indirizzo dalla whitelist
     * @return true se l'indirizzo è stato rimosso, false se non era presente
     */
    public final boolean removeAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String address) {
        return false;
    }
    
    /**
     * Verifica se un indirizzo è nella whitelist
     */
    public final boolean isAddressWhitelisted(@org.jetbrains.annotations.NotNull()
    java.lang.String address) {
        return false;
    }
    
    /**
     * Restituisce tutti gli indirizzi nella whitelist
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.safestwallet.sw.model.WhitelistEntry> getAllAddresses() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/safestwallet/sw/repository/WhitelistManager$Companion;", "", "()V", "WHITELIST_FILENAME", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}