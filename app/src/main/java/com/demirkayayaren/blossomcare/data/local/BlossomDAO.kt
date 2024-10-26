package com.demirkayayaren.blossomcare.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demirkayayaren.blossomcare.data.model.Blossom
import com.demirkayayaren.blossomcare.data.model.BlossomFav

@Dao
interface BlossomDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(blossom: BlossomFav): Long

    @Query("SELECT * FROM blossoms")
    suspend fun getAllBlossoms(): List<BlossomFav>

    @Delete
    suspend fun deleteBlossom(blossom: BlossomFav)
}