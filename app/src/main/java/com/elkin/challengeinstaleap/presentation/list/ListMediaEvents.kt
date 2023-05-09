package com.elkin.challengeinstaleap.presentation.list

import com.elkin.challengeinstaleap.domain.model.Media

sealed class ListMediaEvents{
    data class OnLoadPopular(val isMovies: Boolean): ListMediaEvents()
    data class OnLoadByGenre(val idGenre: Int): ListMediaEvents()
    data class OnNavigateDetail(val item: Media): ListMediaEvents()
    object OnNavigateUp: ListMediaEvents()
}
