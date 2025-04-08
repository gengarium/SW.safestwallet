package com.safestwallet.sw.ui.transaction

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.safestwallet.sw.R
import com.safestwallet.sw.blockchain.AccountManager
import com.safestwallet.sw.viewmodels.TransactionState
import com.safestwallet.sw.viewmodels.TransactionViewModel
import com.safestwallet.sw.viewmodels.WalletViewModel
import timber.log.Timber
import java.math.BigDecimal
import java.math.RoundingMode

class TransactionFragment : Fragment() {

    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var walletViewModel: WalletViewModel
    
    // Form elements
    private lateinit var fromWalletText: TextView
    private lateinit var toAddressEdit: EditText
    private lateinit var amountEdit: EditText
    private lateinit var feeInfoText: TextView
    private lateinit var continueButton: Button
    private lateinit var pasteAddressButton: Button
    
    // OTP elements
    private lateinit var transactionDetailsText: TextView
    private lateinit var otpEdit: EditText
    private lateinit var confirmButton: Button
    private lateinit var cancelButton: Button
    
    // Success elements
    private lateinit var transactionSuccessDetailsText: TextView
    private lateinit var transactionSignatureText: TextView
    private lateinit var closeButton: Button
    
    // Error elements
    private lateinit var errorMessageText: TextView
    private lateinit var tryAgainButton: Button
    
    // Loading elements
    private lateinit var loadingMessageText: TextView
    
    // Layouts for different states
    private lateinit var transactionFormLayout: LinearLayout
    private lateinit var otpVerificationLayout: LinearLayout
    private lateinit var transactionSuccessLayout: LinearLayout
    private lateinit var loadingLayout: LinearLayout
    private lateinit var errorLayout: LinearLayout
    
    // Current transaction data
    private var currentAmount: Double = 0.0
    private var currentFee: Double = 0.0
    private var currentToAddress: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize ViewModels
        transactionViewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]
        walletViewModel = ViewModelProvider(requireActivity())[WalletViewModel::class.java]
        
        // Find views
        initializeViews(view)
        
        // Setup UI
        setupUI()
        
        // Observe ViewModel states
        observeViewModels()
    }
    
    private fun initializeViews(view: View) {
        // Form elements
        fromWalletText = view.findViewById(R.id.text_from_wallet)
        toAddressEdit = view.findViewById(R.id.edit_to_address)
        amountEdit = view.findViewById(R.id.edit_amount)
        feeInfoText = view.findViewById(R.id.text_fee_info)
        continueButton = view.findViewById(R.id.button_continue_transaction)
        pasteAddressButton = view.findViewById(R.id.button_paste_address)
        
        // OTP elements
        transactionDetailsText = view.findViewById(R.id.text_transaction_details)
        otpEdit = view.findViewById(R.id.edit_otp)
        confirmButton = view.findViewById(R.id.button_confirm_transaction)
        cancelButton = view.findViewById(R.id.button_cancel_transaction)
        
        // Success elements
        transactionSuccessDetailsText = view.findViewById(R.id.text_transaction_success_details)
        transactionSignatureText = view.findViewById(R.id.text_transaction_signature)
        closeButton = view.findViewById(R.id.button_close_transaction)
        
        // Error elements
        errorMessageText = view.findViewById(R.id.text_error_message)
        tryAgainButton = view.findViewById(R.id.button_try_again)
        
        // Loading elements
        loadingMessageText = view.findViewById(R.id.text_loading_message)
        
        // Layouts
        transactionFormLayout = view.findViewById(R.id.layout_transaction_form)
        otpVerificationLayout = view.findViewById(R.id.layout_otp_verification)
        transactionSuccessLayout = view.findViewById(R.id.layout_transaction_success)
        loadingLayout = view.findViewById(R.id.layout_loading)
        errorLayout = view.findViewById(R.id.layout_error)
    }
    
    private fun setupUI() {
        // Show wallet info
        val wallet = walletViewModel.selectedWallet.value
        val balance = walletViewModel.walletBalance.value
        
        if (wallet != null) {
            val shortAddress = AccountManager.formatShortAddress(wallet.publicKey)
            fromWalletText.text = "${wallet.name} ($shortAddress)"
        }
        
        // Amount change listener
        amountEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            
            override fun afterTextChanged(s: Editable?) {
                updateFeeInfo()
            }
        })
        
        // Button listeners
        continueButton.setOnClickListener {
            proceedWithTransaction()
        }
        
        pasteAddressButton.setOnClickListener {
            pasteAddressFromClipboard()
        }
        
        confirmButton.setOnClickListener {
            confirmTransaction()
        }
        
        cancelButton.setOnClickListener {
            transactionViewModel.cancelTransaction()
        }
        
        closeButton.setOnClickListener {
            findNavController().popBackStack()
        }
        
        tryAgainButton.setOnClickListener {
            resetTransactionForm()
        }
        
        // Initially show only the form
        showOnlyTransactionForm()
    }
    
    private fun observeViewModels() {
        // Observe transaction state
        transactionViewModel.transactionState.observe(viewLifecycleOwner) { state ->
            handleTransactionState(state)
        }
        
        // Observe wallet info
        walletViewModel.loadedWallet.observe(viewLifecycleOwner) { loadedWallet ->
            if (loadedWallet != null) {
                val shortAddress = AccountManager.formatShortAddress(loadedWallet.wallet.publicKey)
                fromWalletText.text = "${loadedWallet.wallet.name} ($shortAddress)"
            }
        }
    }
    
    private fun handleTransactionState(state: TransactionState) {
        when (state) {
            is TransactionState.INITIALIZING -> {
                showLoading("Initializing transaction...")
            }
            
            is TransactionState.WAITING_FOR_OTP -> {
                showOtpVerification()
            }
            
            is TransactionState.VERIFYING_OTP -> {
                showLoading("Verifying OTP and processing transaction...")
            }
            
            is TransactionState.SUCCESS -> {
                showTransactionSuccess(state.signature)
            }
            
            is TransactionState.CANCELED -> {
                showOnlyTransactionForm()
            }
            
            is TransactionState.ERROR_INSUFFICIENT_FUNDS -> {
                showError("Insufficient funds for this transaction.")
            }
            
            is TransactionState.ERROR_NOT_WHITELISTED -> {
                showError("The recipient address is not in your whitelist.")
            }
            
            is TransactionState.ERROR_INVALID_OTP -> {
                // Just show error message but keep OTP screen
                Toast.makeText(context, "Invalid OTP code. Please try again.", Toast.LENGTH_SHORT).show()
            }
            
            is TransactionState.ERROR -> {
                showError(state.message)
            }
        }
    }
    
    private fun showOnlyTransactionForm() {
        transactionFormLayout.visibility = View.VISIBLE
        otpVerificationLayout.visibility = View.GONE
        transactionSuccessLayout.visibility = View.GONE
        loadingLayout.visibility = View.GONE
        errorLayout.visibility = View.GONE
    }
    
    private fun showOtpVerification() {
        // Update transaction details
        val amountText = String.format("%.6f", currentAmount)
        val feeText = String.format("%.6f", currentFee)
        val totalText = String.format("%.6f", currentAmount + currentFee)
        
        transactionDetailsText.text = "Sending $amountText SOL\n" +
                "To: ${AccountManager.formatShortAddress(currentToAddress)}\n" +
                "Fee: $feeText SOL\n" +
                "Total: $totalText SOL"
        
        // Show OTP layout
        transactionFormLayout.visibility = View.GONE
        otpVerificationLayout.visibility = View.VISIBLE
        transactionSuccessLayout.visibility = View.GONE
        loadingLayout.visibility = View.GONE
        errorLayout.visibility = View.GONE
        
        // Focus on OTP field
        otpEdit.setText("")
        otpEdit.requestFocus()
    }
    
    private fun showTransactionSuccess(signature: String) {
        val amountText = String.format("%.6f", currentAmount)
        
        transactionSuccessDetailsText.text = "$amountText SOL has been sent successfully."
        transactionSignatureText.text = "Signature: $signature"
        
        transactionFormLayout.visibility = View.GONE
        otpVerificationLayout.visibility = View.GONE
        transactionSuccessLayout.visibility = View.VISIBLE
        loadingLayout.visibility = View.GONE
        errorLayout.visibility = View.GONE
    }
    
    private fun showLoading(message: String) {
        loadingMessageText.text = message
        
        transactionFormLayout.visibility = View.GONE
        otpVerificationLayout.visibility = View.GONE
        transactionSuccessLayout.visibility = View.GONE
        loadingLayout.visibility = View.VISIBLE
        errorLayout.visibility = View.GONE
    }
    
    private fun showError(message: String) {
        errorMessageText.text = message
        
        transactionFormLayout.visibility = View.GONE
        otpVerificationLayout.visibility = View.GONE
        transactionSuccessLayout.visibility = View.GONE
        loadingLayout.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
    }
    
    private fun updateFeeInfo() {
        val amountStr = amountEdit.text.toString()
        if (amountStr.isNotEmpty()) {
            try {
                val amount = amountStr.toDouble()
                currentAmount = amount
                currentFee = amount * 0.02
                
                val feeText = String.format("%.6f", currentFee)
                feeInfoText.text = "Fee (2%): $feeText SOL"
            } catch (e: NumberFormatException) {
                feeInfoText.text = "Fee (2%): 0.00 SOL"
            }
        } else {
            feeInfoText.text = "Fee (2%): 0.00 SOL"
        }
    }
    
    private fun proceedWithTransaction() {
        // Validate inputs
        val toAddress = toAddressEdit.text.toString().trim()
        val amountStr = amountEdit.text.toString().trim()
        
        if (toAddress.isEmpty()) {
            Toast.makeText(context, "Please enter a recipient address", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (amountStr.isEmpty()) {
            Toast.makeText(context, "Please enter an amount", Toast.LENGTH_SHORT).show()
            return
        }
        
        try {
            val amount = amountStr.toDouble()
            if (amount <= 0) {
                Toast.makeText(context, "Amount must be greater than 0", Toast.LENGTH_SHORT).show()
                return
            }
            
            // Save current transaction data
            currentAmount = amount
            currentFee = amount * 0.02
            currentToAddress = toAddress
            
            // Get loaded wallet
            val loadedWallet = walletViewModel.loadedWallet.value
            if (loadedWallet == null) {
                Toast.makeText(context, "Wallet not loaded properly", Toast.LENGTH_SHORT).show()
                return
            }
            
            // Start transaction
            transactionViewModel.startTransaction(loadedWallet, toAddress, amount)
            
        } catch (e: NumberFormatException) {
            Toast.makeText(context, "Invalid amount", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun confirmTransaction() {
        val otp = otpEdit.text.toString().trim()
        
        if (otp.length != 6) {
            Toast.makeText(context, "Please enter a valid 6-digit OTP", Toast.LENGTH_SHORT).show()
            return
        }
        
        // Get loaded wallet
        val loadedWallet = walletViewModel.loadedWallet.value
        if (loadedWallet == null) {
            Toast.makeText(context, "Wallet not loaded properly", Toast.LENGTH_SHORT).show()
            return
        }
        
        // Verify OTP and complete transaction
        transactionViewModel.verifyOtpAndCompleteTransaction(loadedWallet, otp)
    }
    
    private fun pasteAddressFromClipboard() {
        val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = clipboard.primaryClip
        
        if (clip != null && clip.itemCount > 0) {
            val text = clip.getItemAt(0).text.toString()
            toAddressEdit.setText(text)
            Toast.makeText(context, "Address pasted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Nothing to paste", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun resetTransactionForm() {
        toAddressEdit.setText("")
        amountEdit.setText("")
        feeInfoText.text = "Fee (2%): 0.00 SOL"
        showOnlyTransactionForm()
    }
}