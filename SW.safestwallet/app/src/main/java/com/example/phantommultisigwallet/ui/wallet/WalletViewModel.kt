package com.example.phantommultisigwallet.ui.wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phantommultisigwallet.model.MultiSigWallet
import kotlinx.coroutines.launch

class WalletViewModel : ViewModel() {
    private val _selectedWallet = MutableLiveData<MultiSigWallet>()
    val selectedWallet: LiveData<MultiSigWallet> = _selectedWallet
    
    private val _balance = MutableLiveData<Double>()
    val balance: LiveData<Double> = _balance
    
    fun setWallet(wallet: MultiSigWallet) {
        _selectedWallet.value = wallet
    }
    
    fun updateBalance(balance: Double) {
        _balance.value = balance
    }
    
    fun refreshBalance(blockchainClient: BlockchainClient) {
        viewModelScope.launch {
            selectedWallet.value?.let { wallet ->
                val newBalance = blockchainClient.getBalance(wallet.address)
                _balance.value = newBalance
            }
        }
    }
}