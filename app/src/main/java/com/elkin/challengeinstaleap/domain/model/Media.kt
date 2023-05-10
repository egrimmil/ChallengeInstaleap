package com.elkin.challengeinstaleap.domain.model

data class Media(
    var id: Int = 0,
    var title: String = "",
    var original_language: String = "",
    var poster_path: String = "",
    var media_type: String = "",
    var genre_ids: List<Int> = emptyList(),
)
