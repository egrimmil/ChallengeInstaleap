package com.elkin.challengeinstaleap.core

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elkin.challengeinstaleap.data.local.dao.DetailMediaDao
import com.elkin.challengeinstaleap.data.local.dao.GenresDao
import com.elkin.challengeinstaleap.data.local.dao.MediaDao
import com.elkin.challengeinstaleap.data.local.entities.DetailMediaEntity
import com.elkin.challengeinstaleap.data.local.entities.MediaEntity
import com.elkin.challengeinstaleap.domain.model.GenresMedia
import javax.inject.Inject

@Database(
    entities = [MediaEntity::class, DetailMediaEntity::class, GenresMedia::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun mediaDao(): MediaDao
    abstract fun detailMediaDao(): DetailMediaDao
    abstract fun genresDao(): GenresDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "ChalllengeInstaleap"
            ).build()
    }
}