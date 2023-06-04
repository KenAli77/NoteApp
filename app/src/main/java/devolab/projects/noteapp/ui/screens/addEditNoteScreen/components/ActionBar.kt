package devolab.projects.noteapp.ui.screens.addEditNoteScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import devolab.projects.noteapp.ui.theme.NeonYellow

@Composable
fun AddNoteActionBar(modifier: Modifier, onAddNote: () -> Unit, onCancel: () -> Unit) {

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = { onCancel() },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, Color.DarkGray),
                modifier = Modifier.padding(20.dp),
                elevation = ButtonDefaults.elevation(0.dp)
            ) {
                Text(
                    text = "cancel",
                    style = MaterialTheme.typography.button,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp)
                )
            }

            Button(
                onClick = { onAddNote() },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = NeonYellow, Color.DarkGray),
                modifier = Modifier.padding(20.dp),
                border = BorderStroke(width = 1.dp, Color.DarkGray),
                elevation = ButtonDefaults.elevation(0.dp)
            ) {
                Text(
                    text = "save",
                    style = MaterialTheme.typography.button,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp)
                )
            }

        }

    }

}
