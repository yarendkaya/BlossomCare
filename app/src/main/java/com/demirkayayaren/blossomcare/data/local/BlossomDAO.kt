package com.demirkayayaren.blossomcare.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demirkayayaren.blossomcare.data.model.Blossom
import dagger.Binds

@Dao
interface BlossomDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(blossom: Blossom): Long

    @Query("SELECT * FROM blossoms")
    suspend fun getAllBlossoms(): List<Blossom>

    @Delete
    suspend fun deleteBlossom(blossom: Blossom)
}