package com.example.phantommultisigwallet.util

import java.security.MessageDigest
import java.security.SecureRandom
import kotlin.experimental.and
import kotlin.experimental.or

object Mnemonic {
    private val secureRandom = SecureRandom()
    private val wordList = listOf(
        // This would be the full BIP-39 word list (2048 words)
        // For brevity, only showing a few sample words
        "abandon", "ability", "able", "about", "above", "absent", "absorb", "abstract",
        // ... rest of the word list ...
        "yard", "year", "yellow", "you", "young", "youth", "zebra", "zero", "zone", "zoo"
    )
    
    fun generateMnemonic(strength: Int = 128): String {
        val entropy = ByteArray(strength / 8)
        secureRandom.nextBytes(entropy)
        
        // Add checksum
        val checksumLength = entropy.size * 8 / 32
        val sha256 = MessageDigest.getInstance("SHA-256").digest(entropy)
        val checksum = sha256[0].toInt() shr (8 - checksumLength)
        
        // Convert to mnemonic
        val result = mutableListOf<String>()
        var buffer = 0
        var bufferBits = 0
        
        for (i in entropy.indices) {
            buffer = (buffer shl 8) or (entropy[i].toInt() and 0xFF)
            bufferBits += 8
            
            while (bufferBits >= 11) {
                bufferBits -= 11
                val index = (buffer shr bufferBits) and 0x7FF
                result.add(wordList[index])
            }
        }
        
        // Add checksum word
        buffer = (buffer shl checksumLength) or checksum
        bufferBits += checksumLength
        val index = (buffer shr (bufferBits - 11)) and 0x7FF
        result.add(wordList[index])
        
        return result.joinToString(" ")
    }
    
    fun fromPrivateKey(privateKey: ByteArray): String {
        // For simplicity, creating a deterministic mnemonic from the private key
        // In a real implementation, this would follow BIP-39 more precisely
        val digest = MessageDigest.getInstance("SHA-256").digest(privateKey)
        val entropy = ByteArray(16)
        System.arraycopy(digest, 0, entropy, 0, 16)
        
        val result = mutableListOf<String>()
        for (i in 0 until 12) {
            val index = ((entropy[i * 4 / 3] and 0xFF.toByte()) * 256 + 
                        (entropy[i * 4 / 3 + 1] and 0xFF.toByte())) % wordList.size
            result.add(wordList[index])
        }
        
        return result.joinToString(" ")
    }
    
    fun toPrivateKey(mnemonic: String): ByteArray {
        // Convert mnemonic to seed (simplified version)
        val words = mnemonic.split(" ")
        val indices = words.map { wordList.indexOf(it) }
        
        // Generate private key from indices
        val privateKey = ByteArray(32)
        val md = MessageDigest.getInstance("SHA-256")
        
        for (i in indices.indices) {
            md.update((indices[i]).toByte())
        }
        
        val hash = md.digest()
        System.arraycopy(hash, 0, privateKey, 0, Math.min(hash.size, privateKey.size))
        
        return privateKey
    }
}