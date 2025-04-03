package com.safestwallet.sw.service

import com.safestwallet.sw.model.MultiSigWallet
import com.safestwallet.sw.model.Transaction
import com.safestwallet.sw.repository.WalletRepository
import com.safestwallet.sw.repository.WhitelistManager

class TransactionService(
    private val walletRepository: WalletRepository,
    private val whitelistManager: WhitelistManager,
    private val blockchainClient: BlockchainClient
) {
    
    suspend fun sendTransaction(
        wallet: MultiSigWallet,
        recipientAddress: String,
        amount: Double,
        memo: String? = null
    ): Result<String> {
        // Check if recipient is whitelisted
        if (!whitelistManager.isWhitelisted(recipientAddress)) {
            return Result.failure(Exception("User not in whitelist"))
        }
        
        // Create transaction
        val transaction = Transaction(
            sender = wallet.address,
            recipient = recipientAddress,
            amount = amount,
            timestamp = System.currentTimeMillis(),
            memo = memo
        )
        
        // Sign with multi-sig (both keys)
        val signedTransaction = walletRepository.signTransaction(transaction, wallet)
        
        // Submit to blockchain
        return try {
            val txId = blockchainClient.submitTransaction(signedTransaction)
            Result.success(txId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}