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

//    @TypeConverter
//    fun toScientificNames(scientificNames: String): List<String> {
//        return scientificNames.split(",")
//    }
//
//    @TypeConverter
//    fun fromScientificNames(scientificNames: List<String>): String {
//        return scientificNames.joinToString(",")
//    }
//
//    @TypeConverter
//    fun toSunlight(sunlight: String): List<String> {
//        return sunlight.split(",")
//    }
//
//    @TypeConverter
//    fun fromSunlight(sunlight: List<String>): String {
//        return sunlight.joinToString(",")
//    }
//
//    @TypeConverter
//    fun toCycle(cycle: String): List<String> {
//        return cycle.split(",")
//    }
//
//    @TypeConverter
//    fun fromCycle(cycle: List<String>): String {
//        return cycle.joinToString(",")
//    }
//
//    @TypeConverter
//    fun toWatering(watering: String): List<String> {
//        return watering.split(",")
//    }
//
//    @TypeConverter
//    fun fromWatering(watering: List<String>): String {
//        return watering.joinToString(",")
//    }
}