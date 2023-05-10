package com.elkin.challengeinstaleap.data.mapper

import com.elkin.challengeinstaleap.core.util.Const
import com.elkin.challengeinstaleap.data.local.entities.MediaEntity
import com.elkin.challengeinstaleap.data.remote.dto.MediaDto
import com.elkin.challengeinstaleap.data.remote.dto.TrendingDto
import com.elkin.challengeinstaleap.domain.model.Media
import com.google.gson.Gson

fun MediaDto.toMedia(): Media{
    return Media(
        id,
        title,
        original_language,
        poster_path = Const.URL_IMG + poster_path,
        media_type,
        genre_ids
    )
}

fun MediaDto.toMediaEntity(): MediaEntity{
    return MediaEntity(
        id,
        title,
        original_language,
        poster_path = poster_path,
        media_type,
        Gson().toJson(genre_ids)
    )
}

fun MediaEntity.toMedia(): Media{
    return Media(
        id = id,
        title = title,
        original_language = original_language,
        poster_path = Const.URL_IMG + poster_path,
        media_type = media_type,
        genre_ids = Gson().fromJson(genre_ids, Array<Int>::class.java).toList()
    )
}