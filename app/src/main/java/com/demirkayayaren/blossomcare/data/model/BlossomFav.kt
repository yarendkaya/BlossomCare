package com.demirkayayaren.blossomcare.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "blossoms"
)
data class BlossomFav(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val commonName: String,
    val cycle: String,
    val defaultImage: String,
    val otherName: List<String>,
    val scientificName: List<String>,
    val sunlight: List<String>,
    val watering: String
)
