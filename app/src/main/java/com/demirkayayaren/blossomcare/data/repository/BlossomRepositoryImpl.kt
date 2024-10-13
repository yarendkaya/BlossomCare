package com.demirkayayaren.blossomcare.data.repository

import com.demirkayayaren.blossomcare.data.model.BlossomResponse
import com.demirkayayaren.blossomcare.data.network.BlossomApi
import com.demirkayayaren.blossomcare.data.network.NetworkResult
import com.demirkayayaren.blossomcare.data.network.RetrofitClient


class BlossomRepositoryImpl(private val api: BlossomApi) : BlossomRepository {
    override suspend fun getAllBlossoms(): NetworkResult<BlossomResponse> {
        return try {
            val response = RetrofitClient.instance.getAllBlossoms()
            if (response.isSuccessful) {
                val returnValue = convertToNotNullable(response.body())
                NetworkResult.Success(returnValue)
            } else {
                NetworkResult.Error("Veri alınamadı")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.localizedMessage)
        }
    }

    private fun convertToNotNullable(blossomResponse: BlossomResponse?): BlossomResponse {
        return BlossomResponse(
            currentPage = blossomResponse?.currentPage ?: 1,
            blossomList = blossomResponse?.blossomList ?: emptyList(),
            from = blossomResponse?.from ?: 0,
            lastPage = blossomResponse?.lastPage ?: 0,
            perPage = blossomResponse?.perPage ?: 30,
            to = blossomResponse?.to ?: 0,
            total = blossomResponse?.total ?: 0
        )
    }
}