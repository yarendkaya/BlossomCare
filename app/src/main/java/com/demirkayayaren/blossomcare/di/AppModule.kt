package com.demirkayayaren.blossomcare.di

import com.demirkayayaren.blossomcare.data.network.BlossomApi
import com.demirkayayaren.blossomcare.data.network.RetrofitClient
import com.demirkayayaren.blossomcare.data.repository.BlossomRepository
import com.demirkayayaren.blossomcare.data.repository.BlossomRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyApi(): BlossomApi {
        return RetrofitClient.instance
    }

    @Provides
    fun provideMyRepository(api: BlossomApi): BlossomRepository {
        return BlossomRepositoryImpl(api)
    }
}