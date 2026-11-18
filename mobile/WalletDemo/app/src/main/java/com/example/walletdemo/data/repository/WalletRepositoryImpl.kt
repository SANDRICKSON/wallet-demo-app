package com.example.walletdemo.data.repository

import com.example.walletdemo.data.remote.WalletApi
import com.example.walletdemo.data.remote.toDomain
import com.example.walletdemo.domain.model.Wallet
import com.example.walletdemo.domain.repository.WalletRepository

class WalletRepositoryImpl(
    private val api: WalletApi
) : WalletRepository {

    override suspend fun getWallet(): Wallet {

        val dto = api.getWallet()

        return dto.toDomain()
    }
}