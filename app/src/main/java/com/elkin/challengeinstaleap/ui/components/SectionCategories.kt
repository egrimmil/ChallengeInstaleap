package com.elkin.challengeinstaleap.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.elkin.challengeinstaleap.R
import com.elkin.challengeinstaleap.domain.model.Media
import com.elkin.challengeinstaleap.ui.theme.fonts

@Composable
fun SectionCategories(
    title: String = "",
    items: List<Media> = emptyList(),
    onNavigateDetail: (Media) -> Unit?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.dp_12),
                    start = dimensionResource(id = R.dimen.dp_8),
                    end = dimensionResource(id = R.dimen.dp_8),
                    bottom = dimensionResource(id = R.dimen.dp_8)
                ),
            textAlign = TextAlign.Start,
            fontFamily = fonts,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )
        ItemsHorizontal(items, onNavigateDetail)
    }
}