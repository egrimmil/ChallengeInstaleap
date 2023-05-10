package com.elkin.challengeinstaleap.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @param genre_ids save list genres in jsonString
 */

@Entity(tableName = "Media")
data class MediaEntity(
    @PrimaryKey var id: Int = 0,
    var title: String = "",
    var original_language: String = "",
    var poster_path: String = "",
    var media_type: String = "",
    var genre_ids: String = "",
)
