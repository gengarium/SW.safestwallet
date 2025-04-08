package com.safestwallet.sw.util

/**
 * Implementazione di Base58 per la codifica/decodifica di bytes
 */
object Base58 {
    private const val ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz"
    private val INDEXES = IntArray(128)

    init {
        for (i in INDEXES.indices) {
            INDEXES[i] = -1
        }
        for (i in ALPHABET.indices) {
            INDEXES[ALPHABET[i].code] = i
        }
    }

    /**
     * Decodifica una stringa Base58 in un array di bytes
     */
    fun decode(input: String): ByteArray {
        if (input.isEmpty()) {
            return ByteArray(0)
        }
        
        val input58 = ByteArray(input.length)
        
        // Trasforma la stringa in base58
        for (i in input.indices) {
            val c = input[i]
            val digit = if (c.code < 128) INDEXES[c.code] else -1
            
            if (digit < 0) {
                throw IllegalArgumentException("Illegal character $c at position $i")
            }
            
            input58[i] = digit.toByte()
        }
        
        // Conta gli zeri iniziali
        var zeros = 0
        while (zeros < input58.size && input58[zeros].toInt() == 0) {
            ++zeros
        }
        
        // Converti da base58 a base256
        val decoded = ByteArray(input.length)
        var outputStart = decoded.size
        
        for (inputStart in zeros until input58.size) {
            decoded[--outputStart] = 0
            
            // Decodifica base58 in base256
            var carry = input58[inputStart].toInt()
            var i = decoded.size - 1
            
            while (carry > 0 || i > outputStart) {
                carry += 58 * (decoded[i].toInt() and 0xff)
                decoded[i] = (carry % 256).toByte()
                carry /= 256
                i--
            }
        }
        
        // Ignora gli extra zeri in base58
        while (outputStart < decoded.size && decoded[outputStart].toInt() == 0) {
            ++outputStart
        }
        
        // Aggiunta degli zeri iniziali
        val temp = ByteArray(zeros + (decoded.size - outputStart))
        System.arraycopy(decoded, outputStart, temp, zeros, decoded.size - outputStart)
        
        return temp
    }

    /**
     * Codifica un array di bytes in una stringa Base58
     */
    fun encode(input: ByteArray): String {
        if (input.isEmpty()) {
            return ""
        }
        
        // Conta gli zeri iniziali
        var zeros = 0
        while (zeros < input.size && input[zeros].toInt() == 0) {
            ++zeros
        }
        
        // Converti da base256 a base58
        val encoded = ByteArray(input.size * 2)
        var outputStart = encoded.size
        
        for (inputStart in zeros until input.size) {
            encoded[--outputStart] = 0
            
            // Codifica base256 in base58
            var carry = input[inputStart].toInt() and 0xff
            var i = encoded.size - 1
            
            while (carry > 0 || i > outputStart) {
                carry += 256 * (encoded[i].toInt() and 0xff)
                encoded[i] = (carry % 58).toByte()
                carry /= 58
                i--
            }
        }
        
        // Ignora gli extra zeri in base256
        while (outputStart < encoded.size && encoded[outputStart].toInt() == 0) {
            ++outputStart
        }
        
        // Aggiunta degli uni iniziali
        val builder = StringBuilder(zeros + (encoded.size - outputStart))
        
        // Aggiungi gli '1' per ogni zero iniziale
        for (i in 0 until zeros) {
            builder.append('1')
        }
        
        // Converte i byte in caratteri dell'alfabeto Base58
        for (i in outputStart until encoded.size) {
            builder.append(ALPHABET[encoded[i].toInt()])
        }
        
        return builder.toString()
    }
}