package devolab.projects.noteapp.ui.screens.notes.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ChipDefaults.chipColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import devolab.projects.noteapp.ui.theme.NeonYellow
import devolab.projects.noteapp.ui.utils.HORIZONTAL_MAIN_PADDING

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TagsScrollView(
    tags: List<String>,
    onTagSelected: (String, Boolean) -> Unit,
    modifier: Modifier
) {
    val lazyListState = rememberLazyListState()



    LazyRow(
        state = lazyListState,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = HORIZONTAL_MAIN_PADDING.dp),

        ) {
        items(tags) { tag ->

            var isSelected by remember { mutableStateOf(false) }

            var backgroundColor by remember { mutableStateOf(Color.White) }

            backgroundColor = if (isSelected) {
                NeonYellow
            } else {
                Color.White
            }

            Chip(
                onClick = {
                    isSelected = !isSelected
                    onTagSelected(tag, isSelected)
                },
                border = BorderStroke(1.dp, color = Color.DarkGray),
                colors = chipColors(
                    backgroundColor = backgroundColor,
                    contentColor = Color.DarkGray
                ),
                modifier = Modifier.padding(horizontal = 7.dp),
                shape = RoundedCornerShape(15.dp),

                ) {

                Text(
                    text = tag,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 6.dp),
                    fontWeight = FontWeight.SemiBold
                )

            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TagItem(tag: String, isSelected: Boolean, onSelect: (String) -> Unit) {

    Chip(onClick = { onSelect(tag) }, border = BorderStroke(1.dp, color = Color.DarkGray)) {

        Text(text = tag, style = MaterialTheme.typography.button)

    }

}