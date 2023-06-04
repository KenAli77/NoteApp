package devolab.projects.noteapp.ui.screens.addEditNoteScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import devolab.projects.noteapp.ui.utils.HORIZONTAL_MAIN_PADDING

@Composable
fun ColorSelectorRow(
    modifier: Modifier,
    onColorSelected: (Int) -> Unit,
    colors: List<Int>,
    selectedColor: Color
) {

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = HORIZONTAL_MAIN_PADDING.dp)
    ) {

        items(colors) { color ->

            val borderWidth = if (selectedColor == Color(color)) {
                2.dp
            } else {
                0.dp
            }

            Surface(
                shape = CircleShape,
                color = Color(color),
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .clickable {
                        onColorSelected(color)
                    }
                    .size(45.dp),
                border = BorderStroke(borderWidth,Color.Black),
            ) {

            }

        }

    }


}

