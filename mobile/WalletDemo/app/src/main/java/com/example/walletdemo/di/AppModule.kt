package com.example.walletdemo.di


import com.example.walletdemo.data.remote.WalletApi
import com.example.walletdemo.data.repository.WalletRepositoryImpl
import com.example.walletdemo.domain.repository.WalletRepository
import com.example.walletdemo.domain.usecase.GetWalletUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideWalletApi(
        retrofit: Retrofit
    ): WalletApi {

        return retrofit.create(WalletApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWalletRepository(
        api: WalletApi
    ): WalletRepository {

        return WalletRepositoryImpl(api)
    }

    @Provides
    fun provideGetWalletUseCase(
        repository: WalletRepository
    ): GetWalletUseCase {

        return GetWalletUseCase(repository)
    }
}