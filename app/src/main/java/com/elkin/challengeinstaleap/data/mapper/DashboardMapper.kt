package com.elkin.challengeinstaleap.data.mapper

import com.elkin.challengeinstaleap.core.util.Const
import com.elkin.challengeinstaleap.data.remote.dto.MediaDto
import com.elkin.challengeinstaleap.data.remote.dto.TrendingDto
import com.elkin.challengeinstaleap.domain.model.Media

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