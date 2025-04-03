package com.safestwallet.sw.service

import com.safestwallet.sw.model.SignedTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class BlockchainClient {
    // This is a mock implementation for demonstration purposes
    
    suspend fun submitTransaction(signedTransaction: SignedTransaction): String {
        return withContext(Dispatchers.IO) {
            // In a real implementation, this would submit to the blockchain network
            // For now, just simulate network delay and return a fake transaction ID
            Thread.sleep(1500) // Simulate network delay
            
            // Generate a random transaction ID
            UUID.randomUUID().toString()
        }
    }
    
    suspend fun getBalance(address: String): Double {
        return withContext(Dispatchers.IO) {
            // In a real implementation, this would query the blockchain
            // For demo purposes, return a random balance
            Random().nextDouble() * 10.0
        }
    }
}