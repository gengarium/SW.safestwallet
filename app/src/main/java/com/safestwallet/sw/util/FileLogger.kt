package com.safestwallet.sw.util

import android.content.Context
import android.util.Log
import timber.log.Timber
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Logger che salva i log in un file, utile per debug in produzione
 */
class FileLogger(private val context: Context) : Timber.Tree() {
    
    private val logDir: File by lazy {
        File(context.getExternalFilesDir(null), "logs").apply {
            if (!exists()) mkdirs()
        }
    }
    
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
    
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        try {
            val logFile = File(logDir, "wallet_log_${getCurrentDate()}.txt")
            
            FileWriter(logFile, true).use { writer ->
                val priorityChar = when (priority) {
                    Log.VERBOSE -> 'V'
                    Log.DEBUG -> 'D'
                    Log.INFO -> 'I'
                    Log.WARN -> 'W'
                    Log.ERROR -> 'E'
                    Log.ASSERT -> 'A'
                    else -> '?'
                }
                
                val timestamp = dateFormat.format(Date())
                writer.append("$timestamp $priorityChar/$tag: $message\n")
                
                if (t != null) {
                    writer.append("${t.message}\n")
                    t.stackTrace.forEach { element ->
                        writer.append("    at $element\n")
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("FileLogger", "Error writing to log file", e)
        }
    }
    
    private fun getCurrentDate(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }
    
    companion object {
        fun getAllLogs(context: Context): List<String> {
            val logDir = File(context.getExternalFilesDir(null), "logs")
            return if (logDir.exists()) {
                logDir.listFiles()?.map { it.name } ?: emptyList()
            } else {
                emptyList()
            }
        }
        
        fun getLogContent(context: Context, logFileName: String): String {
            val logFile = File(File(context.getExternalFilesDir(null), "logs"), logFileName)
            return if (logFile.exists()) {
                logFile.readText()
            } else {
                "Log file not found"
            }
        }
    }
}
