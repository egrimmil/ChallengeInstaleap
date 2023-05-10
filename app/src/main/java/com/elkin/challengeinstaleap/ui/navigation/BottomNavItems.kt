package com.elkin.challengeinstaleap.ui.navigation

import com.elkin.challengeinstaleap.R

sealed class BottomNavItems(
    var title: String,
    var icon: Int,
    var route: String
) {
    object Home :
        BottomNavItems("Inicio", R.drawable.ic_home, Route.DASHBOARD)
    object Movies :
        BottomNavItems("Películas", R.drawable.ic_list, Route.LIST_MEDIA.replace("{isMovie}", "true"))

    object Tvs :
        BottomNavItems("Series", R.drawable.ic_list, Route.LIST_MEDIA.replace("{isMovie}", "false"))
}
