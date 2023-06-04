package devolab.projects.noteapp.ui.screens.addEditNoteScreen.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import devolab.projects.noteapp.ui.theme.DarkBlue
import devolab.projects.noteapp.ui.utils.HORIZONTAL_MAIN_PADDING

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagEditor(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onAddTag: (String) -> Unit,
    tags: List<String>,
    onRemove: (String) -> Unit,
) {

    val scrollState = rememberScrollState()
    Surface(
        shape = RoundedCornerShape(20.dp), modifier = modifier
            .fillMaxWidth()
            .padding(
                HORIZONTAL_MAIN_PADDING.dp
            ),
        border = BorderStroke(1.dp,Color.DarkGray)
    ) {
        FlowRow(modifier = modifier.horizontalScroll(scrollState)) {

            for (tag in tags) {
                if (tag.isNotEmpty()) {
                    TagItem(tag = tag, onRemove = { onRemove(tag) }, modifier = Modifier.padding(5.dp))
                }
            }


            TextField(
                value = value, onValueChange = onValueChange, keyboardActions = KeyboardActions {
                    if (value.isNotBlank()) {
                        onAddTag(value)
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = DarkBlue,
                    textColor = Color.DarkGray,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = { Text(text = "add a tag..") }

            )


        }

    }

}

@Composable
fun TagItem(tag: String, onRemove: (String) -> Unit, modifier: Modifier) {

    Surface(
        modifier = modifier.padding(vertical = 5.dp), shape = RoundedCornerShape(30.dp), border = BorderStroke(
            1.dp,
            Color.LightGray
        )
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(35.dp).padding(horizontal = 5.dp)
        ) {

            Text(text = "#$tag", color = Color.DarkGray)
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Rounded.Cancel,
                contentDescription = null,
                tint = Color.DarkGray,
                modifier = Modifier.clickable { onRemove(tag) }
            )


        }

    }

}