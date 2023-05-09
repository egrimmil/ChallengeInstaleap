package com.elkin.challengeinstaleap.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.elkin.challengeinstaleap.R
import com.elkin.challengeinstaleap.domain.model.Media
import com.elkin.challengeinstaleap.ui.theme.fonts

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeatureMedia(
    modifier: Modifier = Modifier,
    infoMedia: Media,
    onNavigateDetail: (Media) -> Unit?
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        onClick = { onNavigateDetail(infoMedia) }
    ) {
        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .height(550.dp)
        ) {
            val (imgMedia, contentMedia) = createRefs()

            Row(
                modifier = modifier
                    .constrainAs(imgMedia) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = dimensionResource(id = R.dimen.dp_12))
            ) {
                ItemMedia(
                    imgItem = infoMedia.poster_path,
                    item = infoMedia,
                    modifier = modifier,
                    onNavigateDetail = onNavigateDetail
                )
            }
            Row(
                modifier = modifier
                    .constrainAs(contentMedia) {
                        bottom.linkTo(imgMedia.bottom)
                        start.linkTo(imgMedia.start)
                        end.linkTo(imgMedia.end)
                    }
                    .paint(
                        painter = painterResource(R.drawable.bg_degradate),
                        contentScale = ContentScale.FillBounds
                    )
                    .padding(dimensionResource(id = R.dimen.dp_12)),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = if (infoMedia.media_type.lowercase() == "movie")
                        stringResource(id = R.string.movie).uppercase()
                    else
                        stringResource(id = R.string.tv).uppercase(),
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.dp_8)),
                    textAlign = TextAlign.Center,
                    fontFamily = fonts,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_info),
                        contentDescription = stringResource(
                            id = R.string.info
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.info),
                        textAlign = TextAlign.Center,
                        fontFamily = fonts,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}