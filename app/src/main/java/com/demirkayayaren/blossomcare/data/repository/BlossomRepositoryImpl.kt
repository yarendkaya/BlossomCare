package com.demirkayayaren.blossomcare.data.repository

import com.demirkayayaren.blossomcare.data.model.BlossomResponse
import com.demirkayayaren.blossomcare.data.network.NetworkResult
import com.demirkayayaren.blossomcare.data.network.RetrofitClient
import retrofit2.Response


class BlossomRepositoryImpl : BlossomRepository {
        override  suspend fun getAllBlossoms(): NetworkResult<BlossomResponse> {
        return RetrofitClient.instance.getAllBlossoms()
    }
}