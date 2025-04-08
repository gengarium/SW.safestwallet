package com.safestwallet.sw.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.safestwallet.sw.R
import com.safestwallet.sw.viewmodels.WalletViewModel
import timber.log.Timber

class HomeFragment : Fragment() {

    private lateinit var walletViewModel: WalletViewModel
    private lateinit var textHome: TextView
    private lateinit var textBalance: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("HomeFragment: onCreateView")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // TextView not in layout, using text_title instead
        textHome = view.findViewById(R.id.text_title)
        textBalance = view.findViewById(R.id.text_balance)
        
        // Inizializza il ViewModel
        walletViewModel = ViewModelProvider(requireActivity())[WalletViewModel::class.java]
        
        // Osserva i wallet disponibili
        walletViewModel.allWallets.observe(viewLifecycleOwner) { wallets ->
            if (wallets.isEmpty()) {
                textHome.text = getString(R.string.welcome_message) + "\nNessun wallet disponibile. Crea o importa un wallet."
            } else {
                textHome.text = getString(R.string.welcome_message) + "\nWallet disponibili: ${wallets.size}"
                
                // Se un wallet è già selezionato, usa quello, altrimenti seleziona il primo
                val currentSelectedWallet = walletViewModel.selectedWallet.value
                if (currentSelectedWallet == null && wallets.isNotEmpty()) {
                    walletViewModel.selectWallet(wallets[0].id)
                }
            }
        }
        
        // Osserva il wallet selezionato
        walletViewModel.selectedWallet.observe(viewLifecycleOwner) { wallet ->
            if (wallet != null) {
                Timber.d("Selected wallet: ${wallet.name} (${wallet.publicKey})")
                walletViewModel.loadWalletBalance(wallet.publicKey)
            }
        }
        
        // Osserva il saldo del wallet
        walletViewModel.walletBalance.observe(viewLifecycleOwner) { balance ->
            if (balance != null) {
                textBalance.text = "$balance SOL"
            } else {
                textBalance.text = "0.00 SOL"
            }
        }
        
        // Osserva lo stato delle operazioni
        walletViewModel.operationStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                WalletViewModel.OperationStatus.NO_WALLETS -> {
                    textHome.text = getString(R.string.welcome_message) + "\nNessun wallet disponibile. Crea o importa un wallet."
                }
                WalletViewModel.OperationStatus.ERROR -> {
                    Timber.e("Errore nell'operazione sul wallet")
                }
                else -> {
                    // Altri stati gestiti altrove
                }
            }
        }
        
        // Controlla se ci sono wallet
        walletViewModel.hasWallets()
    }
}