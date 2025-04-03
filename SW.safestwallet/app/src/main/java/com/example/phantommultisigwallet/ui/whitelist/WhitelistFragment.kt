package com.example.phantommultisigwallet.ui.whitelist

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phantommultisigwallet.databinding.DialogAddAddressBinding
import com.example.phantommultisigwallet.databinding.FragmentWhitelistBinding
import com.example.phantommultisigwallet.repository.WhitelistManager

class WhitelistFragment : Fragment() {
    private lateinit var binding: FragmentWhitelistBinding
    private lateinit var whitelistManager: WhitelistManager
    private val whitelistAdapter = WhitelistAdapter()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWhitelistBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        whitelistManager = WhitelistManager(requireContext())
        
        // Setup RecyclerView
        binding.recyclerViewWhitelist.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = whitelistAdapter
        }
        
        binding.fabAddAddress.setOnClickListener {
            showAddAddressDialog()
        }
        
        binding.btnImportWhitelist.setOnClickListener {
            importWhitelistFromFile()
        }
        
        binding.btnExportWhitelist.setOnClickListener {
            exportWhitelistToFile()
        }
        
        loadWhitelistedAddresses()
    }
    
    private fun loadWhitelistedAddresses() {
        val addresses = whitelistManager.getWhitelistedAddresses()
        whitelistAdapter.submitList(addresses)
        
        // Show empty state if needed
        binding.emptyStateLayout.isVisible = addresses.isEmpty()
    }
    
    private fun showAddAddressDialog() {
        val dialogBinding = DialogAddAddressBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Add to Whitelist")
            .setView(dialogBinding.root)
            .setPositiveButton("Add") { _, _ ->
                val address = dialogBinding.editTextAddress.text.toString()
                if (address.isNotBlank()) {
                    whitelistManager.addToWhitelist(address)
                    loadWhitelistedAddresses()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
        
        dialog.show()
    }
    
    private fun importWhitelistFromFile() {
        // Implementation for importing whitelist from a text file
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "text/plain"
        }
        startActivityForResult(intent, REQUEST_IMPORT_FILE)
    }
    
    private fun exportWhitelistToFile() {
        // Implementation for exporting whitelist to a text file
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE, "whitelist.txt")
        }
        startActivityForResult(intent, REQUEST_EXPORT_FILE)
    }
    
    companion object {
        private const val REQUEST_IMPORT_FILE = 1
        private const val REQUEST_EXPORT_FILE = 2
    }
}