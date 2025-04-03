package com.example.phantommultisigwallet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.phantommultisigwallet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        
        // Setup bottom navigation
        binding.bottomNavigation.setupWithNavController(navController)
        
        // Replace Discover tab with Whitelist
        val menu = binding.bottomNavigation.menu
        menu.findItem(R.id.navigation_discover)?.apply {
            setTitle(R.string.title_whitelist)
            setIcon(R.drawable.ic_whitelist)
        }
    }
}