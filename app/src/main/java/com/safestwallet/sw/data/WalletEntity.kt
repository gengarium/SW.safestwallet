package com.safestwallet.sw.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.safestwallet.sw.model.MultiSigWallet
import com.safestwallet.sw.model.SafeWallet

/**
 * Entità Room per memorizzare i wallet nel database locale
 */
@Entity(tableName = "wallet_table")
data class WalletEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val publicKey: String,
    val encryptedPrivateKey1: String,
    val encryptedPrivateKey2: String,
    val encryptedSeedPhrase: String,
    val encryptedOtpSeed: String,
    val timeCreated: Long
) {
    /**
     * Converte l'entità in un oggetto del modello
     */
    fun toMultiSigWallet(): MultiSigWallet {
        return MultiSigWallet(
            id = id,
            name = name,
            publicKey = publicKey,
            encryptedPrivateKey1 = encryptedPrivateKey1,
            encryptedPrivateKey2 = encryptedPrivateKey2,
            encryptedSeedPhrase = encryptedSeedPhrase,
            encryptedOtpSeed = encryptedOtpSeed,
            timeCreated = timeCreated
        )
    }
    
    /**
     * Converte l'entità in un oggetto del modello sicuro
     */
    fun toSafeWallet(): SafeWallet {
        return SafeWallet(
            id = id,
            name = name,
            publicKey = publicKey,
            timeCreated = timeCreated
        )
    }
    
    companion object {
        /**
         * Crea un'entità da un oggetto del modello
         */
        fun fromMultiSigWallet(wallet: MultiSigWallet): WalletEntity {
            return WalletEntity(
                id = wallet.id,
                name = wallet.name,
                publicKey = wallet.publicKey,
                encryptedPrivateKey1 = wallet.encryptedPrivateKey1,
                encryptedPrivateKey2 = wallet.encryptedPrivateKey2,
                encryptedSeedPhrase = wallet.encryptedSeedPhrase,
                encryptedOtpSeed = wallet.encryptedOtpSeed,
                timeCreated = wallet.timeCreated
            )
        }
    }
}