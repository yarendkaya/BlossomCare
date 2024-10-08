package com.demirkayayaren.blossomcare.data.repository

import com.demirkayayaren.blossomcare.data.model.BlossomResponse
import com.demirkayayaren.blossomcare.data.network.NetworkResult

import retrofit2.Response

interface BlossomRepository {
    suspend fun getAllBlossoms(): NetworkResult<BlossomResponse>
}