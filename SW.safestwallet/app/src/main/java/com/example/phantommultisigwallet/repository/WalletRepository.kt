package com.example.phantommultisigwallet.repository

import android.content.Context
import android.util.Log
import com.example.phantommultisigwallet.model.MultiSigWallet
import com.example.phantommultisigwallet.model.SignedTransaction
import com.example.phantommultisigwallet.model.Transaction
import com.example.phantommultisigwallet.service.KeyStore

class WalletRepository(private val context: Context) {
    private val keyStore = KeyStore()
    
    fun generateMultiSigWallet(): MultiSigWallet {
        // Generate two separate private keys for the multi-sig wallet
        val privateKey1 = keyStore.generatePrivateKey()
        val privateKey2 = keyStore.generatePrivateKey()
        
        // Create the multi-signature wallet with both keys
        return MultiSigWallet(privateKey1, privateKey2)
    }
    
    fun restoreWallet(seed1: String, seed2: String): MultiSigWallet? {
        try {
            val privateKey1 = keyStore.privateKeyFromSeed(seed1)
            val privateKey2 = keyStore.privateKeyFromSeed(seed2)
            return MultiSigWallet(privateKey1, privateKey2)
        } catch (e: Exception) {
            Log.e("WalletRepository", "Error restoring wallet", e)
            return null
        }
    }
    
    fun signTransaction(transaction: Transaction, wallet: MultiSigWallet): SignedTransaction {
        // Requires both keys to sign the transaction
        val signature1 = wallet.signWithKey1(transaction.data)
        val signature2 = wallet.signWithKey2(transaction.data)
        
        return SignedTransaction(
            transaction = transaction,
            signature1 = signature1,
            signature2 = signature2
        )
    }
}