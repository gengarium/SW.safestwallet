package com.safestwallet.sw.viewmodels;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0016J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u001a\u001a\u00020\u0014J\u000e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/safestwallet/sw/viewmodels/WhitelistViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_operationStatus", "Landroidx/lifecycle/MutableLiveData;", "Lcom/safestwallet/sw/viewmodels/WhitelistViewModel$OperationStatus;", "_whitelistEntries", "", "Lcom/safestwallet/sw/model/WhitelistEntry;", "operationStatus", "Landroidx/lifecycle/LiveData;", "getOperationStatus", "()Landroidx/lifecycle/LiveData;", "whitelistEntries", "getWhitelistEntries", "whitelistManager", "Lcom/safestwallet/sw/repository/WhitelistManager;", "addAddress", "", "address", "", "name", "isAddressWhitelisted", "", "loadWhitelist", "removeAddress", "OperationStatus", "app_debug"})
public final class WhitelistViewModel extends androidx.lifecycle.AndroidViewModel {
    private final com.safestwallet.sw.repository.WhitelistManager whitelistManager = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.safestwallet.sw.model.WhitelistEntry>> _whitelistEntries = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.safestwallet.sw.model.WhitelistEntry>> whitelistEntries = null;
    private final androidx.lifecycle.MutableLiveData<com.safestwallet.sw.viewmodels.WhitelistViewModel.OperationStatus> _operationStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.safestwallet.sw.viewmodels.WhitelistViewModel.OperationStatus> operationStatus = null;
    
    public WhitelistViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.safestwallet.sw.model.WhitelistEntry>> getWhitelistEntries() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.safestwallet.sw.viewmodels.WhitelistViewModel.OperationStatus> getOperationStatus() {
        return null;
    }
    
    /**
     * Carica tutti gli indirizzi nella whitelist
     */
    public final void loadWhitelist() {
    }
    
    /**
     * Aggiunge un indirizzo alla whitelist
     */
    public final void addAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    /**
     * Rimuove un indirizzo dalla whitelist
     */
    public final void removeAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String address) {
    }
    
    /**
     * Verifica se un indirizzo è presente nella whitelist
     */
    public final boolean isAddressWhitelisted(@org.jetbrains.annotations.NotNull()
    java.lang.String address) {
        return false;
    }
    
    /**
     * Stati possibili per le operazioni sulla whitelist
     */
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/safestwallet/sw/viewmodels/WhitelistViewModel$OperationStatus;", "", "(Ljava/lang/String;I)V", "LOADING", "SUCCESS", "ERROR", "ALREADY_EXISTS", "app_debug"})
    public static enum OperationStatus {
        /*public static final*/ LOADING /* = new LOADING() */,
        /*public static final*/ SUCCESS /* = new SUCCESS() */,
        /*public static final*/ ERROR /* = new ERROR() */,
        /*public static final*/ ALREADY_EXISTS /* = new ALREADY_EXISTS() */;
        
        OperationStatus() {
        }
    }
}