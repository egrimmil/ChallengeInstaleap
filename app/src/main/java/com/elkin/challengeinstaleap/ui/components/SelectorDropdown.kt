package com.elkin.challengeinstaleap.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.elkin.challengeinstaleap.R
import com.elkin.challengeinstaleap.ui.theme.fonts
import com.elkin.challengeinstaleap.ui.theme.transparent

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectorDropdown(
    items: List<String>,
    itemSelect: (String) -> Unit?
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(items[0]) }

    Box(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.dp_12))
            .wrapContentWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.White.copy(alpha = ContentAlpha.medium),
                ),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        modifier = Modifier.background(transparent),
                        onClick = {
                            selectedText = item
                            expanded = false
                            itemSelect.invoke(item)
                        }
                    ) {
                        Text(
                            text = item,
                            fontFamily = fonts,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevSelectorDropdown(){
    val list = listOf("All", "Anime", "Bdsm", "Shorts")
    SelectorDropdown(list){}
}