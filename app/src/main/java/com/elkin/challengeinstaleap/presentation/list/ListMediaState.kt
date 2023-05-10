package com.elkin.challengeinstaleap.presentation.list

import com.elkin.challengeinstaleap.domain.model.GenresMedia
import com.elkin.challengeinstaleap.domain.model.Media

data class ListMediaState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isErrorGenres: Boolean = false,
    val message: String = "",
    val popularFirst: Media = Media(),
    val listMedia: MutableList<Media> = mutableListOf(),
    val genres: MutableList<GenresMedia> = mutableListOf(),
    val listMediaOrigin: MutableList<Media> = mutableListOf()
)
