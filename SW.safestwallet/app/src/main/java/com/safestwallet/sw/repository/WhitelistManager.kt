package com.safestwallet.sw.repository

import android.content.Context
import android.util.Log

class WhitelistManager(private val context: Context) {
    private val whitelistFile = "whitelist.txt"
    private val whitelist = mutableSetOf<String>()
    
    init {
        loadWhitelist()
    }
    
    private fun loadWhitelist() {
        try {
            context.openFileInput(whitelistFile).bufferedReader().useLines { lines ->
                lines.forEach { address ->
                    if (address.isNotBlank()) {
                        whitelist.add(address.trim())
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("WhitelistManager", "Error loading whitelist", e)
            // Create the file if it doesn't exist
            saveWhitelist()
        }
    }
    
    fun saveWhitelist() {
        try {
            context.openFileOutput(whitelistFile, Context.MODE_PRIVATE).use { output ->
                whitelist.forEach { address ->
                    output.write("$address\n".toByteArray())
                }
            }
        } catch (e: Exception) {
            Log.e("WhitelistManager", "Error saving whitelist", e)
        }
    }
    
    fun addToWhitelist(address: String): Boolean {
        val result = whitelist.add(address.trim())
        if (result) {
            saveWhitelist()
        }
        return result
    }
    
    fun removeFromWhitelist(address: String): Boolean {
        val result = whitelist.remove(address.trim())
        if (result) {
            saveWhitelist()
        }
        return result
    }
    
    fun isWhitelisted(address: String): Boolean {
        return whitelist.contains(address.trim())
    }
    
    fun getWhitelistedAddresses(): List<String> {
        return whitelist.toList()
    }
}
