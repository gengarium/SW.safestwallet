package com.safestwallet.sw.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.safestwallet.sw.model.WhitelistEntry
import com.safestwallet.sw.repository.WhitelistManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WhitelistViewModel(application: Application) : AndroidViewModel(application) {
    
    private val whitelistManager = WhitelistManager(application)
    
    private val _whitelistEntries = MutableLiveData<List<WhitelistEntry>>()
    val whitelistEntries: LiveData<List<WhitelistEntry>> = _whitelistEntries
    
    private val _operationStatus = MutableLiveData<OperationStatus>()
    val operationStatus: LiveData<OperationStatus> = _operationStatus
    
    init {
        loadWhitelist()
    }
    
    /**
     * Carica tutti gli indirizzi nella whitelist
     */
    fun loadWhitelist() {
        viewModelScope.launch {
            try {
                val entries = withContext(Dispatchers.IO) {
                    whitelistManager.getAllAddresses()
                }
                _whitelistEntries.value = entries
            } catch (e: Exception) {
                _operationStatus.value = OperationStatus.ERROR
            }
        }
    }
    
    /**
     * Aggiunge un indirizzo alla whitelist
     */
    fun addAddress(address: String, name: String = "") {
        viewModelScope.launch {
            _operationStatus.value = OperationStatus.LOADING
            
            try {
                val success = withContext(Dispatchers.IO) {
                    whitelistManager.addAddress(address, name)
                }
                
                if (success) {
                    loadWhitelist()
                    _operationStatus.value = OperationStatus.SUCCESS
                } else {
                    _operationStatus.value = OperationStatus.ALREADY_EXISTS
                }
            } catch (e: Exception) {
                _operationStatus.value = OperationStatus.ERROR
            }
        }
    }
    
    /**
     * Rimuove un indirizzo dalla whitelist
     */
    fun removeAddress(address: String) {
        viewModelScope.launch {
            _operationStatus.value = OperationStatus.LOADING
            
            try {
                val success = withContext(Dispatchers.IO) {
                    whitelistManager.removeAddress(address)
                }
                
                if (success) {
                    loadWhitelist()
                    _operationStatus.value = OperationStatus.SUCCESS
                } else {
                    _operationStatus.value = OperationStatus.ERROR
                }
            } catch (e: Exception) {
                _operationStatus.value = OperationStatus.ERROR
            }
        }
    }
    
    /**
     * Verifica se un indirizzo è presente nella whitelist
     */
    fun isAddressWhitelisted(address: String): Boolean {
        return whitelistManager.isAddressWhitelisted(address)
    }
    
    /**
     * Stati possibili per le operazioni sulla whitelist
     */
    enum class OperationStatus {
        LOADING, SUCCESS, ERROR, ALREADY_EXISTS
    }
}