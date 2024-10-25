package com.demirkayayaren.blossomcare.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demirkayayaren.blossomcare.data.model.Blossom

@Database(entities = [Blossom::class], version = 1)
abstract class BlossomDatabase : RoomDatabase() {

    abstract fun getBlossomDao(): BlossomDAO

}