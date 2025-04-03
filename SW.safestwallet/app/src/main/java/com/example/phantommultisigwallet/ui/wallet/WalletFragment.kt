package com.safestwallet.sw.ui.wallet

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.safestwallet.sw.R
import com.safestwallet.sw.databinding.DialogSeedPhraseBinding
import com.safestwallet.sw.databinding.FragmentWalletBinding
import com.safestwallet.sw.repository.WalletRepository
import com.safestwallet.sw.service.BlockchainClient

class WalletFragment : Fragment() {
    private lateinit var binding: FragmentWalletBinding
    private val viewModel: WalletViewModel by activityViewModels()
    private lateinit var walletRepository: WalletRepository
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWalletBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        walletRepository = WalletRepository(requireContext())
        val blockchainClient = BlockchainClient()
        
        binding.buttonCreateWallet.setOnClickListener {
            createNewWallet()
        }
        
        binding.buttonRestoreWallet.setOnClickListener {
            showRestoreWalletDialog()
        }
        
        binding.buttonSend.setOnClickListener {
            findNavController().navigate(R.id.action_wallet_to_send_transaction)
        }
        
        binding.buttonBackup.setOnClickListener {
            viewModel.selectedWallet.value?.let { wallet ->
                showBackupKeysDialog(wallet.getBackupPhrase1(), wallet.getBackupPhrase2())
            }
        }
        
        viewModel.selectedWallet.observe(viewLifecycleOwner) { wallet ->
            if (wallet != null) {
                binding.textViewWalletAddress.text = wallet.address
                binding.noWalletLayout.visibility = View.GONE
                binding.walletDetailsLayout.visibility = View.VISIBLE
                
                // Refresh balance when wallet is selected
                viewModel.refreshBalance(blockchainClient)
            } else {
                binding.noWalletLayout.visibility = View.VISIBLE
                binding.walletDetailsLayout.visibility = View.GONE
            }
        }
        
        viewModel.balance.observe(viewLifecycleOwner) { balance ->
            binding.textViewBalance.text = String.format("%.5f SOL", balance)
        }
    }
    
    private fun createNewWallet() {
        val wallet = walletRepository.generateMultiSigWallet()
        viewModel.setWallet(wallet)
        
        // Show backup dialog with seed phrases
        showBackupKeysDialog(wallet.getBackupPhrase1(), wallet.getBackupPhrase2())
    }
    
    private fun showBackupKeysDialog(seed1: String, seed2: String) {
        val dialogBinding = DialogSeedPhraseBinding.inflate(layoutInflater)
        dialogBinding.textViewSeedPhrase1.text = seed1
        dialogBinding.textViewSeedPhrase2.text = seed2
        
        AlertDialog.Builder(requireContext())
            .setTitle("Backup Your Keys")
            .setView(dialogBinding.root)
            .setMessage("IMPORTANT: Write down these two seed phrases. You will need both to recover your multi-signature wallet.")
            .setPositiveButton("I've Saved Both") { _, _ -> }
            .setCancelable(false)
            .create()
            .show()
    }
    
    private fun showRestoreWalletDialog() {
        val dialogBinding = DialogSeedPhraseBinding.inflate(layoutInflater)
        dialogBinding.textInputSeed1.visibility = View.VISIBLE
        dialogBinding.textInputSeed2.visibility = View.VISIBLE
        dialogBinding.textViewSeedPhrase1.visibility = View.GONE
        dialogBinding.textViewSeedPhrase2.visibility = View.GONE
        
        AlertDialog.Builder(requireContext())
            .setTitle("Restore Multi-sig Wallet")
            .setView(dialogBinding.root)
            .setMessage("Enter both seed phrases to restore your multi-signature wallet.")
            .setPositiveButton("Restore") { _, _ ->
                val seed1 = dialogBinding.editTextSeed1.text.toString()
                val seed2 = dialogBinding.editTextSeed2.text.toString()
                
                if (seed1.isNotBlank() && seed2.isNotBlank()) {
                    walletRepository.restoreWallet(seed1, seed2)?.let { wallet ->
                        viewModel.setWallet(wallet)
                    } ?: showError("Invalid seed phrases")
                } else {
                    showError("Both seed phrases are required")
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }
    
    private fun showError(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .create()
            .show()
    }
}