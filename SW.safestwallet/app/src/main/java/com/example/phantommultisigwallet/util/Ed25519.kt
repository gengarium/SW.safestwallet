package com.example.phantommultisigwallet.util

import org.bouncycastle.crypto.generators.Ed25519KeyPairGenerator
import org.bouncycastle.crypto.params.Ed25519KeyGenerationParameters
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters
import org.bouncycastle.crypto.signers.Ed25519Signer
import java.security.SecureRandom

object Ed25519 {
    private val secureRandom = SecureRandom()
    
    fun generateKeyPair(): Pair<ByteArray, ByteArray> {
        val keyPairGenerator = Ed25519KeyPairGenerator()
        keyPairGenerator.init(Ed25519KeyGenerationParameters(secureRandom))
        val keyPair = keyPairGenerator.generateKeyPair()
        
        val privateKey = (keyPair.private as Ed25519PrivateKeyParameters).encodePrivateKey()
        val publicKey = (keyPair.public as Ed25519PublicKeyParameters).encodePublicKey()
        
        return Pair(privateKey, publicKey)
    }
    
    fun publicKeyFromPrivateKey(privateKey: ByteArray): ByteArray {
        val privateKeyParams = Ed25519PrivateKeyParameters(privateKey, 0)
        val publicKeyParams = privateKeyParams.generatePublicKey()
        return publicKeyParams.encodePublicKey()
    }
    
    fun sign(message: ByteArray, privateKey: ByteArray): ByteArray {
        val signer = Ed25519Signer()
        val privateKeyParams = Ed25519PrivateKeyParameters(privateKey, 0)
        signer.init(true, privateKeyParams)
        signer.update(message, 0, message.size)
        return signer.generateSignature()
    }
    
    fun verify(message: ByteArray, signature: ByteArray, publicKey: ByteArray): Boolean {
        val verifier = Ed25519Signer()
        val publicKeyParams = Ed25519PublicKeyParameters(publicKey, 0)
        verifier.init(false, publicKeyParams)
        verifier.update(message, 0, message.size)
        return verifier.verifySignature(signature)
    }
}