package com.example.walletdemo.data.remote

import retrofit2.http.GET

interface WalletApi {

    @GET("api/wallet/")
    suspend fun getWallet(): WalletDto

}