package com.elkin.challengeinstaleap.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elkin.challengeinstaleap.data.local.entities.MediaEntity

@Dao
interface MediaDao {
    @Query("SELECT * FROM Media")
    suspend fun getAll(): List<MediaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedia(media: MediaEntity)

    @Query("SELECT * FROM Media WHERE id == :id")
    suspend fun getMediaById(id: Int): MediaEntity

    @Query("SELECT * FROM Media WHERE media_type == :type")
    suspend fun getAllMediaByType(type: String): List<MediaEntity>
}