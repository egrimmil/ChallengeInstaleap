package com.elkin.challengeinstaleap.presentation.detail

import com.elkin.challengeinstaleap.domain.model.DetailMedia

data class DetailState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val message: String = "",
    val mediaInfo: DetailMedia = DetailMedia()
)
