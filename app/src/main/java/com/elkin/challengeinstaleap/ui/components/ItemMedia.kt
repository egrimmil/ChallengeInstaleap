package com.elkin.challengeinstaleap.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import com.elkin.challengeinstaleap.R
import com.elkin.challengeinstaleap.domain.model.Media
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ItemMedia(
    modifier: Modifier = Modifier,
    imgItem: String,
    item: Media = Media(),
    enableClick: Boolean = true,
    onNavigateDetail: (Media) -> Unit?
) {
    Box(
        modifier = modifier
            .clickable(
                enableClick,
                onClick = { onNavigateDetail(item) }
            )
            .padding(dimensionResource(id = R.dimen.dp_4))
    ) {
        GlideImage(
            imageModel = { imgItem },
            imageOptions = ImageOptions(
                contentScale = ContentScale.FillBounds,
                contentDescription = "${item.id}"
            )
        )
    }
}