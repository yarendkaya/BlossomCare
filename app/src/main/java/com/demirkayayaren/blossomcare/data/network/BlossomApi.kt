package com.demirkayayaren.blossomcare.data.network

import com.demirkayayaren.blossomcare.BuildConfig
import com.demirkayayaren.blossomcare.data.model.BlossomResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BlossomApi {
    private val apiKey
        get() = BuildConfig.API_KEY

    @GET("species-list")
 suspend fun getAllBlossoms(
        @Query("key") apiKey: String = this.apiKey,
        @Query("page") page: Int = 1,
    ): Response <BlossomResponse>
}
