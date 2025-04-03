package com.safestwallet.sw.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.safestwallet.sw.R
import com.safestwallet.sw.databinding.FragmentHomeBinding
import com.safestwallet.sw.ui.wallet.WalletViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val walletViewModel: WalletViewModel by activityViewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        walletViewModel.selectedWallet.observe(viewLifecycleOwner) { wallet ->
            if (wallet != null) {
                binding.textViewWalletAddress.text = wallet.address
                binding.emptyStateLayout.visibility = View.GONE
                binding.walletInfoLayout.visibility = View.VISIBLE
            } else {
                binding.emptyStateLayout.visibility = View.VISIBLE
                binding.walletInfoLayout.visibility = View.GONE
            }
        }
        
        walletViewModel.balance.observe(viewLifecycleOwner) { balance ->
            binding.textViewBalance.text = String.format("%.5f SOL", balance)
        }
        
        binding.buttonSend.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_send_transaction)
        }
    }
}