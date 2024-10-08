package com.demirkayayaren.blossomcare.data.model

import com.google.gson.annotations.SerializedName

data class Blossom(
    @SerializedName("common_name")
    val commonName: String,
    val cycle: String,
    @SerializedName("default_image")
    val defaultImage: DefaultImage,
    val id: Int,
    @SerializedName("other_name")
    val otherName: List<String>,
    @SerializedName("scientific_name")
    val scientificName: List<String>,
    val sunlight: List<String>,
    val watering: String
)