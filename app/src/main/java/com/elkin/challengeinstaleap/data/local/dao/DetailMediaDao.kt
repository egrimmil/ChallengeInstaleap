package com.elkin.challengeinstaleap.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elkin.challengeinstaleap.data.local.entities.DetailMediaEntity

@Dao
interface DetailMediaDao {
    @Query("SELECT * FROM DetailMedia")
    suspend fun getAllDetailMedia(): List<DetailMediaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMedia(detailMedia: DetailMediaEntity)

    @Query("SELECT * FROM DetailMedia WHERE id == :id")
    suspend fun getDetailMediaById(id: Int): DetailMediaEntity?
}