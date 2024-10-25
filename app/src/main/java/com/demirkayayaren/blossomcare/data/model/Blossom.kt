package com.demirkayayaren.blossomcare.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(
    tableName = "blossoms"
)
data class Blossom(
    @PrimaryKey(autoGenerate = true)
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
): Serializable