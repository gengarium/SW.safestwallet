package com.safestwallet.sw.ui.transaction

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.safestwallet.sw.R
import com.safestwallet.sw.databinding.FragmentSendTransactionBinding
import com.safestwallet.sw.repository.WalletRepository
import com.safestwallet.sw.repository.WhitelistManager
import com.safestwallet.sw.service.BlockchainClient
import com.safestwallet.sw.service.TransactionService
import com.safestwallet.sw.ui.wallet.WalletViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class SendTransactionFragment : Fragment() {
    private lateinit var binding: FragmentSendTransactionBinding
    private lateinit var transactionService: TransactionService
    private lateinit var whitelistManager: WhitelistManager
    private val viewModel: WalletViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSendTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val walletRepository = WalletRepository(requireContext())
        whitelistManager = WhitelistManager(requireContext())
        val blockchainClient = BlockchainClient()
        transactionService = TransactionService(walletRepository, whitelistManager, blockchainClient)
        
        binding.buttonSend.setOnClickListener {
            sendTransaction()
        }
        
        binding.recipientAddressInput.setEndIconOnClickListener {
            showAddressSelector()
        }
    }
    
    private fun sendTransaction() {
        val recipientAddress = binding.editTextRecipientAddress.text.toString()
        val amountText = binding.editTextAmount.text.toString()
        
        if (recipientAddress.isBlank() || amountText.isBlank()) {
            showError("Please enter recipient address and amount")
            return
        }
        
        val amount = amountText.toDoubleOrNull()
        if (amount == null || amount <= 0) {
            showError("Please enter a valid amount")
            return
        }
        
        viewModel.selectedWallet.value?.let { wallet ->
            // Check whitelist
            if (!whitelistManager.isWhitelisted(recipientAddress)) {
                showError("User not in whitelist")
                return
            }
            
            // Proceed with transaction
            lifecycleScope.launch {
                binding.progressBar.isVisible = true
                binding.buttonSend.isEnabled = false
                
                val result = transactionService.sendTransaction(
                    wallet = wallet,
                    recipientAddress = recipientAddress,
                    amount = amount,
                    memo = binding.editTextMemo.text.toString().takeIf { it.isNotBlank() }
                )
                
                binding.progressBar.isVisible = false
                binding.buttonSend.isEnabled = true
                
                result.fold(
                    onSuccess = { txId ->
                        showSuccess("Transaction sent: $txId")
                        findNavController().popBackStack()
                    },
                    onFailure = { error ->
                        showError(error.message ?: "Transaction failed")
                    }
                )
            }
        } ?: showError("No wallet selected")
    }
    
    private fun showAddressSelector() {
        val addresses = whitelistManager.getWhitelistedAddresses()
        if (addresses.isEmpty()) {
            showError("Whitelist is empty. Add addresses first.")
            return
        }
        
        AlertDialog.Builder(requireContext())
            .setTitle("Select from Whitelist")
            .setItems(addresses.toTypedArray()) { _, which ->
                binding.editTextRecipientAddress.setText(addresses[which])
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.error))
            .show()
    }
    
    private fun showSuccess(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.success))
            .show()
    }
}