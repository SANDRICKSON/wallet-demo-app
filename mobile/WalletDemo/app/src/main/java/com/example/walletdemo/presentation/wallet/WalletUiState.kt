package com.example.walletdemo.presentation.wallet

import com.example.walletdemo.domain.model.Wallet

sealed class WalletUiState {

    data object Loading : WalletUiState()

    data class Success(
        val wallet: Wallet
    ) : WalletUiState()

    data class Error(
        val message: String
    ) : WalletUiState()
}