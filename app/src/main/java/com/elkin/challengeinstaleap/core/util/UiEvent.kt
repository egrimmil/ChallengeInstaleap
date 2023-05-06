package com.elkin.challengeinstaleap.core.util

sealed class UiEvent{
    object NavigateUp : UiEvent()
    data class Navigate(val route: String) : UiEvent()
}
