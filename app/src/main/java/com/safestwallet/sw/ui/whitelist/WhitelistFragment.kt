package com.safestwallet.sw.ui.whitelist

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.safestwallet.sw.R
import com.safestwallet.sw.viewmodels.WhitelistViewModel
import timber.log.Timber

class WhitelistFragment : Fragment() {

    private lateinit var whitelistViewModel: WhitelistViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button
    private lateinit var emptyText: TextView
    private lateinit var adapter: WhitelistAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("WhitelistFragment: onCreateView")
        return inflater.inflate(R.layout.fragment_whitelist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Inizializza le views
        recyclerView = view.findViewById(R.id.recycler_whitelist)
        addButton = view.findViewById(R.id.button_add_address)
        emptyText = view.findViewById(R.id.text_empty_whitelist)
        
        // Configura il RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = WhitelistAdapter(emptyList()) { address ->
            showRemoveAddressDialog(address)
        }
        recyclerView.adapter = adapter
        
        // Inizializza il ViewModel
        whitelistViewModel = ViewModelProvider(this)[WhitelistViewModel::class.java]
        
        // Imposta il listener per il pulsante di aggiunta
        addButton.setOnClickListener {
            showAddAddressDialog()
        }
        
        // Osserva la lista di indirizzi nella whitelist
        whitelistViewModel.whitelistEntries.observe(viewLifecycleOwner) { entries ->
            adapter.updateData(entries)
            
            // Mostra/nascondi il messaggio "lista vuota"
            if (entries.isEmpty()) {
                emptyText.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                emptyText.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }
        
        // Osserva lo stato delle operazioni
        whitelistViewModel.operationStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                WhitelistViewModel.OperationStatus.LOADING -> {
                    // Mostrare un indicatore di caricamento se necessario
                }
                WhitelistViewModel.OperationStatus.SUCCESS -> {
                    Toast.makeText(context, "Operazione completata con successo", Toast.LENGTH_SHORT).show()
                }
                WhitelistViewModel.OperationStatus.ERROR -> {
                    Toast.makeText(context, "Errore durante l'operazione", Toast.LENGTH_SHORT).show()
                }
                WhitelistViewModel.OperationStatus.ALREADY_EXISTS -> {
                    Toast.makeText(context, "Indirizzo già presente nella whitelist", Toast.LENGTH_SHORT).show()
                }
            }
        }
        
        // Carica la whitelist
        whitelistViewModel.loadWhitelist()
    }
    
    private fun showAddAddressDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_address, null)
        val addressEditText = dialogView.findViewById<EditText>(R.id.edit_address)
        val nameEditText = dialogView.findViewById<EditText>(R.id.edit_name)
        
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.add_address_title)
            .setView(dialogView)
            .setPositiveButton(R.string.add) { _, _ ->
                val address = addressEditText.text.toString().trim()
                val name = nameEditText.text.toString().trim()
                
                if (address.isEmpty()) {
                    Toast.makeText(context, R.string.error_invalid_address, Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                
                whitelistViewModel.addAddress(address, name)
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }
    
    private fun showRemoveAddressDialog(address: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Rimuovi Indirizzo")
            .setMessage("Sei sicuro di voler rimuovere questo indirizzo dalla whitelist?")
            .setPositiveButton(R.string.confirm) { _, _ ->
                whitelistViewModel.removeAddress(address)
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }
}