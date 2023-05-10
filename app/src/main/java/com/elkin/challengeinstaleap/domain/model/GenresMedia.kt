package com.elkin.challengeinstaleap.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Genres")
data class GenresMedia(
    @PrimaryKey var id: Int = 0,
    var name: String = "",
)
