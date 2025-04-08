package com.safestwallet.sw

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.safestwallet.sw.util.FileLogger
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Imposta la bottom navigation
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        
        // Configura i frammenti di primo livello (senza back button)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_wallet, R.id.navigation_whitelist
            )
        )
        
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        Timber.i("MainActivity created")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logs -> {
                showLogsDialog()
                true
            }
            R.id.action_settings -> {
                // Qui implementerai l'apertura delle impostazioni
                Timber.d("Settings menu clicked")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLogsDialog() {
        val logFiles = FileLogger.getAllLogs(this)
        
        if (logFiles.isEmpty()) {
            AlertDialog.Builder(this)
                .setTitle(R.string.logs_title)
                .setMessage("Nessun file di log disponibile")
                .setPositiveButton(R.string.confirm, null)
                .show()
            return
        }
        
        AlertDialog.Builder(this)
            .setTitle(R.string.logs_title)
            .setItems(logFiles.toTypedArray()) { _, which ->
                val logContent = FileLogger.getLogContent(this, logFiles[which])
                
                AlertDialog.Builder(this)
                    .setTitle(logFiles[which])
                    .setMessage(logContent)
                    .setPositiveButton(R.string.confirm, null)
                    .show()
            }
            .setPositiveButton(R.string.confirm, null)
            .show()
    }
}