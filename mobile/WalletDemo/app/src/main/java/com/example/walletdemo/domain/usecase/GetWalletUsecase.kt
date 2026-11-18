package com.example.walletdemo.domain.usecase

import com.example.walletdemo.domain.model.Wallet
import com.example.walletdemo.domain.repository.WalletRepository

class GetWalletUseCase(
    private val repository: WalletRepository
) {

    suspend operator fun invoke(): Wallet {

        return repository.getWallet()
    }
}