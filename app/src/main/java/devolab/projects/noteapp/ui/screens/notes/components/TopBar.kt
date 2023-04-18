package devolab.projects.noteapp.ui.screens.notes.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import devolab.projects.noteapp.ui.theme.LightPurple

@Composable
fun NotesScreenTopBar(
    modifier: Modifier,
    onAddButtonClick: () -> Unit,
    title: String = "your notes"
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = MaterialTheme.typography.h1, color = Color.DarkGray)
        AddNoteButton(onClick = { onAddButtonClick() }, modifier = Modifier)
    }
}

@Composable
fun AddNoteButton(onClick: () -> Unit, modifier: Modifier) {


    FloatingActionButton(
        onClick = { onClick() },
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.White,
        modifier = Modifier.border(1.dp, color = Color.DarkGray, shape = RoundedCornerShape(20.dp)),
        elevation = FloatingActionButtonDefaults.elevation(0.dp),
    ) {

        Icon(imageVector = Icons.Rounded.Add, contentDescription = null, tint = Color.Black)

    }


}