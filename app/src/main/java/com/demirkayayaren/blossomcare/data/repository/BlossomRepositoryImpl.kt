package com.demirkayayaren.blossomcare.data.repository

import com.demirkayayaren.blossomcare.data.local.BlossomDAO
import com.demirkayayaren.blossomcare.data.model.BlossomFav
import com.demirkayayaren.blossomcare.data.model.BlossomResponse
import com.demirkayayaren.blossomcare.data.network.BlossomApi
import com.demirkayayaren.blossomcare.data.network.NetworkResult
import javax.inject.Inject


class BlossomRepositoryImpl @Inject constructor(
    private val api: BlossomApi,
    private val dao: BlossomDAO
) : BlossomRepository {
    override suspend fun getAllBlossoms(): NetworkResult<BlossomResponse> {
        return try {
            val response = api.getAllBlossoms()
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

    override suspend fun saveBlossom(blossom: BlossomFav) {
        dao.upsert(blossom)
    }

    override suspend fun deleteBlossom(blossom: BlossomFav) {
        dao.deleteBlossom(blossom)
    }

    override suspend fun getAllSavedBlossoms(): List<BlossomFav> {
        return dao.getAllBlossoms()
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