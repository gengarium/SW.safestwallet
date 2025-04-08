package com.safestwallet.sw.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.safestwallet.sw.R
import com.safestwallet.sw.blockchain.AccountManager
import com.safestwallet.sw.viewmodels.WalletViewModel
import timber.log.Timber

class WalletFragment : Fragment() {

    private lateinit var walletViewModel: WalletViewModel
    
    // Views per la gestione del wallet
    private lateinit var textWalletInfo: TextView
    private lateinit var textWalletAddressDetails: TextView
    private lateinit var layoutNoWallet: View
    private lateinit var layoutWalletDetails: View
    
    // Bottoni per il wallet non creato
    private lateinit var buttonCreateWallet: Button
    private lateinit var buttonImportWallet: Button
    
    // Bottoni per il wallet creato
    private lateinit var buttonSend: Button
    private lateinit var buttonReceive: Button
    private lateinit var buttonBackupWallet: Button
    private lateinit var buttonTransactionHistory: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("WalletFragment: onCreateView")
        return inflater.inflate(R.layout.fragment_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Inizializza le view
        textWalletInfo = view.findViewById(R.id.text_wallet_info)
        textWalletAddressDetails = view.findViewById(R.id.text_wallet_address_details)
        layoutNoWallet = view.findViewById(R.id.layout_no_wallet)
        layoutWalletDetails = view.findViewById(R.id.layout_wallet_details)
        
        // Bottoni per wallet non creato
        buttonCreateWallet = view.findViewById(R.id.button_create_wallet)
        buttonImportWallet = view.findViewById(R.id.button_import_wallet)
        
        // Bottoni per wallet creato
        buttonSend = view.findViewById(R.id.button_send)
        buttonReceive = view.findViewById(R.id.button_receive)
        buttonBackupWallet = view.findViewById(R.id.button_backup_wallet)
        buttonTransactionHistory = view.findViewById(R.id.button_transaction_history)
        
        // Inizializza il ViewModel
        walletViewModel = ViewModelProvider(requireActivity())[WalletViewModel::class.java]
        
        // Imposta i listener
        buttonCreateWallet.setOnClickListener {
            showCreateWalletDialog()
        }
        
        buttonImportWallet.setOnClickListener {
            showImportWalletDialog()
        }
        
        buttonSend.setOnClickListener {
            // Implementare in seguito
            Toast.makeText(context, "Funzionalità di invio in sviluppo", Toast.LENGTH_SHORT).show()
        }
        
        buttonReceive.setOnClickListener {
            showReceiveDialog()
        }
        
        buttonBackupWallet.setOnClickListener {
            // Implementare in seguito
            Toast.makeText(context, "Funzionalità di backup in sviluppo", Toast.LENGTH_SHORT).show()
        }
        
        buttonTransactionHistory.setOnClickListener {
            // Implementare in seguito
            Toast.makeText(context, "Storico transazioni in sviluppo", Toast.LENGTH_SHORT).show()
        }
        
        // Osserva il wallet selezionato
        walletViewModel.selectedWallet.observe(viewLifecycleOwner) { wallet ->
            if (wallet != null) {
                // Aggiorna l'UI per mostrare i dettagli del wallet
                textWalletInfo.text = "Wallet: ${wallet.name}"
                textWalletAddressDetails.text = wallet.publicKey
                
                // Mostra il layout dei dettagli e nascondi quello "no wallet"
                layoutNoWallet.visibility = View.GONE
                layoutWalletDetails.visibility = View.VISIBLE
            } else {
                // Nascondi il layout dei dettagli e mostra quello "no wallet"
                layoutNoWallet.visibility = View.VISIBLE
                layoutWalletDetails.visibility = View.GONE
            }
        }
        
        // Osserva lo stato delle operazioni
        walletViewModel.operationStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                WalletViewModel.OperationStatus.LOADING -> {
                    // Mostrare un indicatore di caricamento se necessario
                }
                WalletViewModel.OperationStatus.SUCCESS -> {
                    Toast.makeText(context, "Operazione completata con successo", Toast.LENGTH_SHORT).show()
                }
                WalletViewModel.OperationStatus.ERROR -> {
                    Toast.makeText(context, "Errore durante l'operazione", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    // Altri stati gestiti altrove
                }
            }
        }
    }
    
    private fun showCreateWalletDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_create_wallet, null)
        val nameEditText = dialogView.findViewById<EditText>(R.id.edit_wallet_name)
        
        AlertDialog.Builder(requireContext())
            .setTitle("Crea Nuovo Wallet")
            .setView(dialogView)
            .setPositiveButton("Crea") { _, _ ->
                val name = nameEditText.text.toString().trim()
                if (name.isEmpty()) {
                    Toast.makeText(context, "Inserisci un nome per il wallet", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                
                walletViewModel.createWallet(name)
            }
            .setNegativeButton("Annulla", null)
            .show()
    }
    
    private fun showImportWalletDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_import_wallet, null)
        val seedEditText = dialogView.findViewById<EditText>(R.id.edit_seed_phrase)
        val nameEditText = dialogView.findViewById<EditText>(R.id.edit_wallet_name)
        
        AlertDialog.Builder(requireContext())
            .setTitle("Importa Wallet")
            .setView(dialogView)
            .setPositiveButton("Importa") { _, _ ->
                val seedPhrase = seedEditText.text.toString().trim()
                val name = nameEditText.text.toString().trim()
                
                if (seedPhrase.isEmpty()) {
                    Toast.makeText(context, "Inserisci la seed phrase", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                
                if (name.isEmpty()) {
                    Toast.makeText(context, "Inserisci un nome per il wallet", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                
                walletViewModel.importWallet(seedPhrase, name)
            }
            .setNegativeButton("Annulla", null)
            .show()
    }
    
    private fun showReceiveDialog() {
        val currentWallet = walletViewModel.selectedWallet.value ?: return
        
        AlertDialog.Builder(requireContext())
            .setTitle("Ricevi SOL")
            .setMessage("Il tuo indirizzo Solana:\n\n${currentWallet.publicKey}")
            .setPositiveButton("Copia") { _, _ ->
                // Copia negli appunti
                val clipboard = requireActivity().getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                val clip = android.content.ClipData.newPlainText("Solana Address", currentWallet.publicKey)
                clipboard.setPrimaryClip(clip)
                
                Toast.makeText(context, "Indirizzo copiato negli appunti", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Chiudi", null)
            .show()
    }
}