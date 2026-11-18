package com.example.walletdemo.domain.repository

import com.example.walletdemo.domain.model.Wallet

interface WalletRepository {

    suspend fun getWallet(): Wallet

}