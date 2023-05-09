package com.elkin.challengeinstaleap.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.elkin.challengeinstaleap.R
import com.elkin.challengeinstaleap.domain.model.Media

@Composable
fun ItemsHorizontal(
    items: List<Media> = emptyList(),
    onNavigateDetail: (Media) -> Unit?
) {
    when {
        items.isNotEmpty() -> {
            LazyRow(contentPadding = PaddingValues(dimensionResource(id = R.dimen.dp_4))) {
                items(items.size) {
                    ItemMedia(
                        imgItem = items[it].poster_path,
                        item = items[it],
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.dp_180))
                            .width(dimensionResource(id = R.dimen.dp_120)),
                        onNavigateDetail = onNavigateDetail
                    )
                }
            }
        }
    }
}