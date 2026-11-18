package com.example.miniwallet.presentation.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walletdemo.domain.usecase.GetWalletUseCase
import com.example.walletdemo.presentation.wallet.WalletUiState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val getWalletUseCase: GetWalletUseCase
) : ViewModel() {

    private val _state =
        MutableStateFlow<WalletUiState>(
            WalletUiState.Loading
        )

    val state: StateFlow<WalletUiState> = _state

    fun loadWallet() {

        viewModelScope.launch {

            _state.value = WalletUiState.Loading

            try {

                val wallet = getWalletUseCase()

                _state.value =
                    WalletUiState.Success(wallet)

            } catch (e: Exception) {

                _state.value =
                    WalletUiState.Error(
                        e.message ?: "Something went wrong"
                    )
            }
        }
    }
}