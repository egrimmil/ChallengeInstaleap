package com.elkin.challengeinstaleap.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @param genre_ids save list genres in jsonString
 * @param seasons save list seasons in jsonString
 */

@Entity(tableName = "DetailMedia")
data class DetailMediaEntity(
    @PrimaryKey var id: Int = 0,
    var name: String = "",
    var overview: String = "",
    var imgDetail: String = "",
    var isMovie: Boolean = true,
    var dateRelease: String = "",
    var genre_ids: String = "",
    var votes: Double = 0.0,
    var runtime: Int = 0,
    var seasons: String = ""
)