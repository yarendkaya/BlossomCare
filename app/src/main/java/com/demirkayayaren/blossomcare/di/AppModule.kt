package com.demirkayayaren.blossomcare.di

import com.demirkayayaren.blossomcare.data.network.BlossomApi

import com.demirkayayaren.blossomcare.data.repository.BlossomRepository
import com.demirkayayaren.blossomcare.data.repository.BlossomRepositoryImpl
import com.demirkayayaren.blossomcare.util.Constants.Companion.BASE_URL
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
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMyApi(retrofit: Retrofit): BlossomApi {
        return retrofit.create(BlossomApi::class.java)
    }

    @Provides
    fun provideMyRepository(api: BlossomApi): BlossomRepository {
        return BlossomRepositoryImpl(api)
    }
}