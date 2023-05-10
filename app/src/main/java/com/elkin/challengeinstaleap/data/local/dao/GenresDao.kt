package com.elkin.challengeinstaleap.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elkin.challengeinstaleap.domain.model.GenresMedia

@Dao
interface GenresDao {
    @Query("SELECT * FROM Genres")
    suspend fun getAllGenres(): List<GenresMedia>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(detailMedia: GenresMedia)

    @Query("SELECT * FROM Genres WHERE id == :id")
    suspend fun getGenreById(id: Int): GenresMedia

    @Query("SELECT * FROM Genres WHERE name == :name")
    suspend fun getGenreByName(name: String): GenresMedia
}