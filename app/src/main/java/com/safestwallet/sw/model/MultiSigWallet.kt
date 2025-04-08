package com.safestwallet.sw.model

import java.io.Serializable

/**
 * Rappresenta un wallet multi-signature per Solana
 */
data class MultiSigWallet(
    val id: String,
    val name: String,
    val publicKey: String,
    val encryptedPrivateKey1: String,
    val encryptedPrivateKey2: String,
    val encryptedSeedPhrase: String,
    val encryptedOtpSeed: String,
    val timeCreated: Long = System.currentTimeMillis()
) : Serializable {

    /**
     * Controlla se il wallet è completamente configurato
     */
    fun isFullyConfigured(): Boolean {
        return publicKey.isNotEmpty() && 
               encryptedPrivateKey1.isNotEmpty() && 
               encryptedPrivateKey2.isNotEmpty() && 
               encryptedSeedPhrase.isNotEmpty() &&
               encryptedOtpSeed.isNotEmpty()
    }
    
    /**
     * Crea una versione sicura del wallet con i dati sensibili rimossi,
     * adatta per essere visualizzata o memorizzata in modo non sicuro
     */
    fun toSafeWallet(): SafeWallet {
        return SafeWallet(
            id = id,
            name = name,
            publicKey = publicKey,
            timeCreated = timeCreated
        )
    }
}

/**
 * Versione del wallet senza dati sensibili
 */
data class SafeWallet(
    val id: String,
    val name: String,
    val publicKey: String,
    val timeCreated: Long
) : Serializable

/**
 * Rappresenta una transazione wallet
 */
data class WalletTransaction(
    val id: String,
    val fromAddress: String,
    val toAddress: String,
    val amount: Double,
    val fee: Double,
    val status: TransactionStatus,
    val timestamp: Long = System.currentTimeMillis(),
    val signature: String? = null,
    val blockHeight: Long? = null
) : Serializable

/**
 * Stati possibili per una transazione
 */
enum class TransactionStatus {
    PENDING,
    CONFIRMED,
    FAILED,
    REJECTED
}

/**
 * Rappresenta un indirizzo nella whitelist
 */
data class WhitelistEntry(
    val address: String,
    val name: String = "",
    val dateAdded: Long = System.currentTimeMillis()
) : Serializable