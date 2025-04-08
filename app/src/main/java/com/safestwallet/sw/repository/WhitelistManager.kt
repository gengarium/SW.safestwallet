package com.safestwallet.sw.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.safestwallet.sw.model.WhitelistEntry
import com.safestwallet.sw.util.CryptoUtils
import timber.log.Timber
import java.io.File

/**
 * Gestisce la whitelist degli indirizzi approvati
 */
class WhitelistManager(private val context: Context) {
    
    private val gson = Gson()
    private val whitelistFile: File
        get() = File(context.filesDir, WHITELIST_FILENAME)
    
    // Cache in memoria della whitelist
    private var whitelist: MutableList<WhitelistEntry>? = null
    
    /**
     * Carica la whitelist dal file crittografato
     */
    fun loadWhitelist(): List<WhitelistEntry> {
        // Return cache if available
        if (whitelist != null) {
            return whitelist!!
        }
        
        try {
            // Check if file exists
            if (!whitelistFile.exists()) {
                Timber.d("Whitelist file does not exist, creating empty whitelist")
                whitelist = mutableListOf()
                return whitelist!!
            }
            
            // Check if encryption key exists
            if (!CryptoUtils.keyExists()) {
                Timber.e("Encryption key does not exist, cannot decrypt whitelist")
                whitelist = mutableListOf()
                return whitelist!!
            }
            
            val encryptedContent = whitelistFile.readText()
            Timber.d("Read encrypted whitelist content (${encryptedContent.length} bytes)")
            
            val decryptedContent = CryptoUtils.decrypt(encryptedContent)
            if (decryptedContent == null) {
                Timber.e("Failed to decrypt whitelist, returning empty list")
                whitelist = mutableListOf()
                return whitelist!!
            }
            
            // Try to parse the JSON
            try {
                val type = object : TypeToken<List<WhitelistEntry>>() {}.type
                whitelist = gson.fromJson<MutableList<WhitelistEntry>>(decryptedContent, type)
                
                // Handle null result from gson
                if (whitelist == null) {
                    Timber.e("Failed to parse whitelist JSON, using empty list")
                    whitelist = mutableListOf()
                }
                
                Timber.d("Loaded ${whitelist!!.size} entries from whitelist")
            } catch (e: Exception) {
                Timber.e(e, "Error parsing whitelist JSON: ${e.message}")
                whitelist = mutableListOf()
            }
            
            return whitelist!!
        } catch (e: Exception) {
            Timber.e(e, "Error loading whitelist: ${e.message}")
            whitelist = mutableListOf()
            return whitelist!!
        }
    }
    
    /**
     * Salva la whitelist in un file crittografato
     */
    private fun saveWhitelist() {
        try {
            // Verifica che la lista non sia null
            if (whitelist == null) {
                Timber.e("Whitelist is null, cannot save")
                return
            }
            
            // Verifica che la chiave di crittografia esista
            if (!CryptoUtils.keyExists()) {
                Timber.i("Encryption key does not exist, creating one")
                val success = CryptoUtils.createSecretKey()
                if (!success) {
                    Timber.e("Failed to create encryption key")
                    return
                }
            }
            
            val jsonContent = gson.toJson(whitelist)
            Timber.d("Preparing to encrypt whitelist JSON: $jsonContent")
            
            val encryptedContent = CryptoUtils.encrypt(jsonContent)
            if (encryptedContent == null) {
                Timber.e("Failed to encrypt whitelist")
                return
            }
            
            whitelistFile.writeText(encryptedContent)
            Timber.d("Saved ${whitelist!!.size} entries to whitelist")
        } catch (e: Exception) {
            Timber.e(e, "Error saving whitelist: ${e.message}")
        }
    }
    
    /**
     * Aggiunge un indirizzo alla whitelist
     * @return true se l'indirizzo è stato aggiunto, false se era già presente
     */
    fun addAddress(address: String, name: String = ""): Boolean {
        loadWhitelist() // Assicura che la whitelist sia caricata
        
        // Verifica che l'indirizzo non sia già presente
        if (whitelist!!.any { it.address == address }) {
            Timber.d("Address already in whitelist: $address")
            return false
        }
        
        // Aggiungi il nuovo indirizzo
        val entry = WhitelistEntry(address = address, name = name)
        whitelist!!.add(entry)
        saveWhitelist()
        
        Timber.d("Added address to whitelist: $address")
        return true
    }
    
    /**
     * Rimuove un indirizzo dalla whitelist
     * @return true se l'indirizzo è stato rimosso, false se non era presente
     */
    fun removeAddress(address: String): Boolean {
        loadWhitelist() // Assicura che la whitelist sia caricata
        
        val initialSize = whitelist!!.size
        whitelist = whitelist!!.filter { it.address != address }.toMutableList()
        
        val removed = initialSize != whitelist!!.size
        if (removed) {
            saveWhitelist()
            Timber.d("Removed address from whitelist: $address")
        } else {
            Timber.d("Address not found in whitelist: $address")
        }
        
        return removed
    }
    
    /**
     * Verifica se un indirizzo è nella whitelist
     */
    fun isAddressWhitelisted(address: String): Boolean {
        loadWhitelist() // Assicura che la whitelist sia caricata
        return whitelist!!.any { it.address == address }
    }
    
    /**
     * Restituisce tutti gli indirizzi nella whitelist
     */
    fun getAllAddresses(): List<WhitelistEntry> {
        return loadWhitelist()
    }
    
    companion object {
        private const val WHITELIST_FILENAME = "whitelist.encrypted"
    }
}
