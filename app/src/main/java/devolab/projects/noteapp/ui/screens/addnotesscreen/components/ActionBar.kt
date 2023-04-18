package devolab.projects.noteapp.ui.screens.addnotesscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.ClosedCaption
import androidx.compose.material.icons.rounded.FormatQuote
import androidx.compose.material.icons.rounded.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import devolab.projects.noteapp.ui.theme.DarkBlue
import devolab.projects.noteapp.ui.theme.NeonYellow

@Composable
fun AddNoteActionBar(modifier: Modifier, onAddNote: () -> Unit, onCancel: () -> Unit) {

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {


        Row(
            horizontalArrangement = Arrangement.spacedBy(35.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(
                    DarkBlue
                )
                .fillMaxWidth(0.7f)

            ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Image,
                    contentDescription = null,
                    tint = Color.White
                )

            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.FormatQuote,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.CheckCircle,
                    contentDescription = null,
                    tint = Color.White,

                    )
            }


        }


        Spacer(modifier = Modifier.height(20.dp))


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
