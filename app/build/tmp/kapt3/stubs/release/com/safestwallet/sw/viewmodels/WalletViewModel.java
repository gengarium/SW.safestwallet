package com.safestwallet.sw.viewmodels;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0001(B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u001fJ\u0006\u0010\"\u001a\u00020\u001dJ\u0016\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u001fJ\u000e\u0010\'\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u001fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0019\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011\u00a8\u0006)"}, d2 = {"Lcom/safestwallet/sw/viewmodels/WalletViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_operationStatus", "Landroidx/lifecycle/MutableLiveData;", "Lcom/safestwallet/sw/viewmodels/WalletViewModel$OperationStatus;", "_selectedWallet", "Lcom/safestwallet/sw/model/MultiSigWallet;", "_walletBalance", "Ljava/math/BigDecimal;", "allWallets", "Landroidx/lifecycle/LiveData;", "", "Lcom/safestwallet/sw/model/SafeWallet;", "getAllWallets", "()Landroidx/lifecycle/LiveData;", "database", "Lcom/safestwallet/sw/data/AppDatabase;", "operationStatus", "getOperationStatus", "repository", "Lcom/safestwallet/sw/repository/WalletRepository;", "selectedWallet", "getSelectedWallet", "walletBalance", "getWalletBalance", "createWallet", "", "name", "", "deleteWallet", "walletId", "hasWallets", "importWallet", "seedPhrase", "loadWalletBalance", "publicKey", "selectWallet", "OperationStatus", "app_release"})
public final class WalletViewModel extends androidx.lifecycle.AndroidViewModel {
    private final com.safestwallet.sw.data.AppDatabase database = null;
    private final com.safestwallet.sw.repository.WalletRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.safestwallet.sw.model.SafeWallet>> allWallets = null;
    private final androidx.lifecycle.MutableLiveData<com.safestwallet.sw.model.MultiSigWallet> _selectedWallet = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.safestwallet.sw.model.MultiSigWallet> selectedWallet = null;
    private final androidx.lifecycle.MutableLiveData<java.math.BigDecimal> _walletBalance = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.math.BigDecimal> walletBalance = null;
    private final androidx.lifecycle.MutableLiveData<com.safestwallet.sw.viewmodels.WalletViewModel.OperationStatus> _operationStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.safestwallet.sw.viewmodels.WalletViewModel.OperationStatus> operationStatus = null;
    
    public WalletViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.safestwallet.sw.model.SafeWallet>> getAllWallets() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.safestwallet.sw.model.MultiSigWallet> getSelectedWallet() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.math.BigDecimal> getWalletBalance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.safestwallet.sw.viewmodels.WalletViewModel.OperationStatus> getOperationStatus() {
        return null;
    }
    
    /**
     * Crea un nuovo wallet
     */
    public final void createWallet(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    /**
     * Importa un wallet da seed phrase
     */
    public final void importWallet(@org.jetbrains.annotations.NotNull()
    java.lang.String seedPhrase, @org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    /**
     * Seleziona un wallet tramite ID
     */
    public final void selectWallet(@org.jetbrains.annotations.NotNull()
    java.lang.String walletId) {
    }
    
    /**
     * Carica il saldo di un wallet
     */
    public final void loadWalletBalance(@org.jetbrains.annotations.NotNull()
    java.lang.String publicKey) {
    }
    
    /**
     * Elimina un wallet
     */
    public final void deleteWallet(@org.jetbrains.annotations.NotNull()
    java.lang.String walletId) {
    }
    
    /**
     * Verifica se esiste almeno un wallet
     */
    public final void hasWallets() {
    }
    
    /**
     * Stati possibili per le operazioni sui wallet
     */
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/safestwallet/sw/viewmodels/WalletViewModel$OperationStatus;", "", "(Ljava/lang/String;I)V", "LOADING", "SUCCESS", "ERROR", "HAS_WALLETS", "NO_WALLETS", "app_release"})
    public static enum OperationStatus {
        /*public static final*/ LOADING /* = new LOADING() */,
        /*public static final*/ SUCCESS /* = new SUCCESS() */,
        /*public static final*/ ERROR /* = new ERROR() */,
        /*public static final*/ HAS_WALLETS /* = new HAS_WALLETS() */,
        /*public static final*/ NO_WALLETS /* = new NO_WALLETS() */;
        
        OperationStatus() {
        }
    }
}