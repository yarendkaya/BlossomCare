package com.demirkayayaren.blossomcare.data.repository

import com.demirkayayaren.blossomcare.data.model.BlossomFav
import com.demirkayayaren.blossomcare.data.model.BlossomResponse
import com.demirkayayaren.blossomcare.data.network.NetworkResult

interface BlossomRepository {
    suspend fun getAllBlossoms(): NetworkResult<BlossomResponse>
    suspend fun saveBlossom(blossom: BlossomFav)
    suspend fun deleteBlossom(blossom: BlossomFav)
    suspend fun getAllSavedBlossoms(): List<BlossomFav>
}