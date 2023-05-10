package com.elkin.challengeinstaleap.domain.model

data class DetailMedia(
    var id: Int = 0,
    var name: String = "",
    var overview: String = "",
    var imgDetail: String = "",
    var isMovie: Boolean = true,
    var dateRelease: String = "",
    var genre_ids: List<GenresMedia> = emptyList(),
    var votes: Double = 0.0,
    var runtime: Int = 0,
    var seasons: List<SeasonsMedia> = emptyList()
)
