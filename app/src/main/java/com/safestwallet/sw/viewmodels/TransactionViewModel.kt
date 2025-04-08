package com.safestwallet.sw.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.safestwallet.sw.blockchain.MultiSigManager
import com.safestwallet.sw.blockchain.OtpManager
import com.safestwallet.sw.blockchain.SolanaClient
import com.safestwallet.sw.blockchain.TransactionResult
import com.safestwallet.sw.data.AppDatabase
import com.safestwallet.sw.model.WalletTransaction
import com.safestwallet.sw.repository.TransactionCompleteResult
import com.safestwallet.sw.repository.TransactionRepository
import com.safestwallet.sw.repository.TransactionStartResult
import com.safestwallet.sw.repository.WhitelistManager
import kotlinx.coroutines.launch
import timber.log.Timber

class TransactionViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val solanaClient = SolanaClient()
    private val multiSigManager = MultiSigManager()
    private val otpManager = OtpManager()
    private val whitelistManager = WhitelistManager(application)
    
    private val repository = TransactionRepository(
        database,
        solanaClient,
        multiSigManager,
        otpManager,
        whitelistManager
    )
    
    // Stato corrente della transazione
    private val _transactionState = MutableLiveData<TransactionState>()
    val transactionState: LiveData<TransactionState> = _transactionState
    
    // Dati di transazione correnti
    private var currentTransactionId: String? = null
    private var currentPreparedTransaction: TransactionResult.TransactionCreated? = null
    
    /**
     * Ottiene le transazioni recenti
     */
    fun getRecentTransactions(): LiveData<List<WalletTransaction>> {
        return repository.getRecentTransactions()
    }
    
    /**
     * Ottiene le transazioni per un wallet
     */
    fun getTransactionsForWallet(walletAddress: String): LiveData<List<WalletTransaction>> {
        return repository.getTransactionsForAddress(walletAddress)
    }
    
    /**
     * Inizia una nuova transazione
     */
    fun startTransaction(wallet: WalletViewModel.LoadedWallet, destination: String, amount: Double) {
        viewModelScope.launch {
            _transactionState.value = TransactionState.INITIALIZING
            
            if (!whitelistManager.isAddressWhitelisted(destination)) {
                _transactionState.value = TransactionState.ERROR_NOT_WHITELISTED
                return@launch
            }
            
            val result = repository.startTransaction(wallet.wallet, destination, amount)
            
            when (result) {
                is TransactionStartResult.Success -> {
                    currentTransactionId = result.transaction.id
                    currentPreparedTransaction = result.preparedTransaction
                    _transactionState.value = TransactionState.WAITING_FOR_OTP
                }
                is TransactionStartResult.Error -> {
                    Timber.e("Error starting transaction: ${result.message}")
                    when {
                        result.message.contains("whitelist") -> {
                            _transactionState.value = TransactionState.ERROR_NOT_WHITELISTED
                        }
                        result.message.contains("funds") -> {
                            _transactionState.value = TransactionState.ERROR_INSUFFICIENT_FUNDS
                        }
                        else -> {
                            _transactionState.value = TransactionState.ERROR(result.message)
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Verifica l'OTP e completa la transazione
     */
    fun verifyOtpAndCompleteTransaction(wallet: WalletViewModel.LoadedWallet, otp: String) {
        viewModelScope.launch {
            val transactionId = currentTransactionId
            val preparedTransaction = currentPreparedTransaction
            
            if (transactionId == null || preparedTransaction == null) {
                _transactionState.value = TransactionState.ERROR("No active transaction")
                return@launch
            }
            
            _transactionState.value = TransactionState.VERIFYING_OTP
            
            val result = repository.completeTransactionWithOtp(
                transactionId,
                wallet.wallet,
                otp,
                preparedTransaction
            )
            
            when (result) {
                is TransactionCompleteResult.Success -> {
                    _transactionState.value = TransactionState.SUCCESS(result.signature)
                    // Resetta i dati della transazione corrente
                    currentTransactionId = null
                    currentPreparedTransaction = null
                }
                is TransactionCompleteResult.Error -> {
                    Timber.e("Error completing transaction: ${result.message}")
                    if (result.message.contains("OTP")) {
                        _transactionState.value = TransactionState.ERROR_INVALID_OTP
                    } else {
                        _transactionState.value = TransactionState.ERROR(result.message)
                    }
                }
            }
        }
    }
    
    /**
     * Cancella la transazione corrente
     */
    fun cancelTransaction() {
        currentTransactionId = null
        currentPreparedTransaction = null
        _transactionState.value = TransactionState.CANCELED
    }
}

/**
 * Stati possibili per una transazione
 */
sealed class TransactionState {
    object INITIALIZING : TransactionState()
    object WAITING_FOR_OTP : TransactionState()
    object VERIFYING_OTP : TransactionState()
    data class SUCCESS(val signature: String) : TransactionState()
    object CANCELED : TransactionState()
    
    // Stati di errore
    object ERROR_INSUFFICIENT_FUNDS : TransactionState()
    object ERROR_NOT_WHITELISTED : TransactionState()
    object ERROR_INVALID_OTP : TransactionState()
    data class ERROR(val message: String) : TransactionState()
}