package com.elkin.challengeinstaleap.presentation.detail

import com.elkin.challengeinstaleap.domain.model.Media

sealed class DetailEvents {
    data class OnLoadService(val item: Media) : DetailEvents()
    object OnNavigateUp: DetailEvents()
}