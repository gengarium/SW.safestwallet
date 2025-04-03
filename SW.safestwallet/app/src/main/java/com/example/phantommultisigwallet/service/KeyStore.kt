package com.example.phantommultisigwallet.service

import com.example.phantommultisigwallet.util.Ed25519
import com.example.phantommultisigwallet.util.Mnemonic
import java.security.SecureRandom

class KeyStore {
    private val secureRandom = SecureRandom()
    
    fun generatePrivateKey(): ByteArray {
        val privateKey = ByteArray(32) // Ed25519 uses 32-byte private keys
        secureRandom.nextBytes(privateKey)
        return privateKey
    }
    
    fun privateKeyFromSeed(seed: String): ByteArray {
        // Convert mnemonic seed phrase to private key
        return Mnemonic.toPrivateKey(seed)
    }
}