package com.example.walletdemo.presentation.wallet

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.miniwallet.presentation.wallet.WalletViewModel
import com.example.walletdemo.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WalletFragment : Fragment(R.layout.fragment_wallet) {

    private val viewModel: WalletViewModel by viewModels()

    private lateinit var balanceText: TextView
    private lateinit var languageButton: Button

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        balanceText = view.findViewById(R.id.balanceText)

        languageButton =
            view.findViewById(R.id.languageButton)

        setupLanguageButton()

        observeState()

        viewModel.loadWallet()
    }

    private fun setupLanguageButton() {

        languageButton.setOnClickListener {

            val currentLanguage =
                AppCompatDelegate
                    .getApplicationLocales()
                    .toLanguageTags()

            if (currentLanguage == "ka") {

                AppCompatDelegate.setApplicationLocales(
                    LocaleListCompat.forLanguageTags("en")
                )

            } else {

                AppCompatDelegate.setApplicationLocales(
                    LocaleListCompat.forLanguageTags("ka")
                )
            }
        }
    }

    private fun observeState() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {

                viewModel.state.collect { state ->

                    when (state) {

                        WalletUiState.Loading -> {

                            balanceText.text = "Loading..."
                        }

                        is WalletUiState.Success -> {

                            val wallet = state.wallet

                            balanceText.text =
                                "${wallet.balance} ${wallet.currency}"
                        }

                        is WalletUiState.Error -> {

                            balanceText.text = "Error"

                            Toast.makeText(
                                requireContext(),
                                state.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}