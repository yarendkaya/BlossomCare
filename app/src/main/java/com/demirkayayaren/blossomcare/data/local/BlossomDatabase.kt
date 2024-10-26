package com.demirkayayaren.blossomcare.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demirkayayaren.blossomcare.data.model.BlossomFav

@TypeConverters(BlossomFavConverter::class)
@Database(entities = [BlossomFav::class], version = 1)
abstract class BlossomDatabase : RoomDatabase() {

    abstract fun getBlossomDao(): BlossomDAO

}