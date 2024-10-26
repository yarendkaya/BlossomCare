package com.demirkayayaren.blossomcare.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
) : Serializable {
    fun convertToBlossomFav(): BlossomFav {
        return BlossomFav(
            id = this.id,
            commonName = this.commonName,
            cycle = this.cycle,
            originalUrl = this.defaultImage.toString(),
            otherName = this.otherName,
            scientificName = this.scientificName,
            sunlight = this.sunlight,
            watering = this.watering,
            thumbNail = this.defaultImage.thumbnail
        )
    }
}