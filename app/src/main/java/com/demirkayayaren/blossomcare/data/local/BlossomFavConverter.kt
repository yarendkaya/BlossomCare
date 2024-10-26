package com.demirkayayaren.blossomcare.data.local

import androidx.room.TypeConverter

class BlossomFavConverter {
    @TypeConverter
    fun toOtherNames(otherNames: String): List<String> {
        return otherNames.split(",")
    }

    @TypeConverter
    fun fromOtherNames(otherNames: List<String>): String {
        return otherNames.joinToString(",")
    }
}