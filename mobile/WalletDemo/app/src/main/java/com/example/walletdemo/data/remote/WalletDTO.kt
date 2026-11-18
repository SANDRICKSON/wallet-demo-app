package com.example.walletdemo.data.remote

import com.example.walletdemo.domain.model.Wallet

data class WalletDto(
    val id: Int,
    val currency: String,
    val balance: Double
)

fun WalletDto.toDomain(): Wallet {

    return Wallet(
        id = id,
        currency = currency,
        balance = balance
    )
}