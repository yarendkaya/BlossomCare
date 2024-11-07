package com.demirkayayaren.blossomcare.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.demirkayayaren.blossomcare.data.local.BlossomDAO
import com.demirkayayaren.blossomcare.data.local.BlossomDatabase
import com.demirkayayaren.blossomcare.data.network.BlossomApi
import com.demirkayayaren.blossomcare.data.repository.BlossomRepository
import com.demirkayayaren.blossomcare.data.repository.BlossomRepositoryImpl
import com.demirkayayaren.blossomcare.ui.BlossomViewModel
import com.demirkayayaren.blossomcare.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BlossomDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            BlossomDatabase::class.java,
            "blossom_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBlossomDao(database: BlossomDatabase): BlossomDAO {
        return database.getBlossomDao()
    }

    @Provides
    @Singleton
    fun provideMyRepository(api: BlossomApi, dao: BlossomDAO): BlossomRepository {
        return BlossomRepositoryImpl(api, dao)
    }

    fun provideMyViewModel(repository: BlossomRepository): BlossomViewModel {
        return BlossomViewModel(repository)

    }
}