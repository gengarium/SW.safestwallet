package com.example.phantommultisigwallet.service

import com.example.phantommultisigwallet.model.MultiSigWallet
import com.example.phantommultisigwallet.model.Transaction
import com.example.phantommultisigwallet.repository.WalletRepository
import com.example.phantommultisigwallet.repository.WhitelistManager

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