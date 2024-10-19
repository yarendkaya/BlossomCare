package com.demirkayayaren.blossomcare.data.model

import com.google.gson.annotations.SerializedName

data class BlossomResponse(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("data")
    val blossomList: List<Blossom>,
    val from: Int,
    @SerializedName("last_page")
    val lastPage: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val to: Int,
    val total: Int
)