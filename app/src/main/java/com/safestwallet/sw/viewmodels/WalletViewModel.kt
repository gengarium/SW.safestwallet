package com.safestwallet.sw.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.safestwallet.sw.blockchain.SolanaClient
import com.safestwallet.sw.data.AppDatabase
import com.safestwallet.sw.model.MultiSigWallet
import com.safestwallet.sw.model.SafeWallet
import com.safestwallet.sw.repository.WalletRepository
import kotlinx.coroutines.launch
import java.math.BigDecimal

class WalletViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val repository = WalletRepository(database)
    
    // Tutti i wallet (versione sicura)
    val allWallets: LiveData<List<SafeWallet>> = repository.getAllWallets()
    
    // Wallet selezionato
    private val _selectedWallet = MutableLiveData<MultiSigWallet?>()
    val selectedWallet: LiveData<MultiSigWallet?> = _selectedWallet
    
    // Saldo del wallet selezionato
    private val _walletBalance = MutableLiveData<BigDecimal?>()
    val walletBalance: LiveData<BigDecimal?> = _walletBalance
    
    // Stato operazioni di wallet
    private val _operationStatus = MutableLiveData<OperationStatus>()
    val operationStatus: LiveData<OperationStatus> = _operationStatus
    
    /**
     * Crea un nuovo wallet
     */
    fun createWallet(name: String) {
        viewModelScope.launch {
            _operationStatus.value = OperationStatus.LOADING
            
            val wallet = repository.createMultiSigWallet(name)
            
            if (wallet != null) {
                _selectedWallet.value = wallet
                _operationStatus.value = OperationStatus.SUCCESS
            } else {
                _operationStatus.value = OperationStatus.ERROR
            }
        }
    }
    
    /**
     * Importa un wallet da seed phrase
     */
    fun importWallet(seedPhrase: String, name: String) {
        viewModelScope.launch {
            _operationStatus.value = OperationStatus.LOADING
            
            val wallet = repository.importWalletFromSeed(seedPhrase, name)
            
            if (wallet != null) {
                _selectedWallet.value = wallet
                _operationStatus.value = OperationStatus.SUCCESS
            } else {
                _operationStatus.value = OperationStatus.ERROR
            }
        }
    }
    
    /**
     * Seleziona un wallet tramite ID
     */
    fun selectWallet(walletId: String) {
        viewModelScope.launch {
            repository.getWalletById(walletId).observeForever { wallet ->
                _selectedWallet.value = wallet
                
                // Quando un wallet viene selezionato, aggiorna il saldo
                wallet?.let { loadWalletBalance(it.publicKey) }
            }
        }
    }
    
    /**
     * Carica il saldo di un wallet
     */
    fun loadWalletBalance(publicKey: String) {
        viewModelScope.launch {
            _walletBalance.value = repository.getWalletBalance(publicKey)
        }
    }
    
    /**
     * Elimina un wallet
     */
    fun deleteWallet(walletId: String) {
        viewModelScope.launch {
            _operationStatus.value = OperationStatus.LOADING
            
            val success = repository.deleteWallet(walletId)
            
            if (success) {
                _selectedWallet.value = null
                _operationStatus.value = OperationStatus.SUCCESS
            } else {
                _operationStatus.value = OperationStatus.ERROR
            }
        }
    }
    
    /**
     * Verifica se esiste almeno un wallet
     */
    fun hasWallets() {
        viewModelScope.launch {
            val count = repository.getWalletCount()
            _operationStatus.value = if (count > 0) {
                OperationStatus.HAS_WALLETS
            } else {
                OperationStatus.NO_WALLETS
            }
        }
    }
    
    /**
     * Stati possibili per le operazioni sui wallet
     */
    enum class OperationStatus {
        LOADING, SUCCESS, ERROR, HAS_WALLETS, NO_WALLETS
    }
}