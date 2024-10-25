package com.demirkayayaren.blossomcare.data.repository

import com.demirkayayaren.blossomcare.data.model.Blossom
import com.demirkayayaren.blossomcare.data.model.BlossomResponse
import com.demirkayayaren.blossomcare.data.network.NetworkResult

interface BlossomRepository {
    suspend fun getAllBlossoms(): NetworkResult<BlossomResponse>
    suspend fun saveBlossom(blossom: Blossom)
    suspend fun deleteBlossom(blossom: Blossom)
    suspend fun getAllSavedBlossoms(): List<Blossom>
}