package devolab.projects.noteapp.ui.screens.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.graphics.toColor
import devolab.projects.noteapp.domain.model.Note
import devolab.projects.noteapp.ui.theme.LightGray
import devolab.projects.noteapp.ui.theme.LightPurple
import devolab.projects.noteapp.ui.utils.HORIZONTAL_MAIN_PADDING
import devolab.projects.noteapp.ui.utils.getRandomColor
import devolab.projects.noteapp.ui.utils.getReadableDateTime

@Composable
fun NotesScrollView(notes: Map<String, List<Note>>, modifier: Modifier) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer { alpha = 0.99F }
            .drawWithContent {
                val colors = listOf(Color.Transparent, Color.White)
                drawContent()
                drawRect(
                    brush = Brush.verticalGradient(colors, startY = 1f, endY = 15.dp.toPx()),
                    blendMode = BlendMode.DstIn
                )
            },
        state = lazyListState,
        contentPadding = PaddingValues(top = 15.dp, bottom = 10.dp)

    ) {
        notes.forEach { (date, notes) ->

            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp, start = HORIZONTAL_MAIN_PADDING.dp),
                    color = Color.White
                ) {
                    Text(
                        date,
                        color = LightGray,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                }
            }


            items(notes) { note ->
                NoteItem(note = note)
            }

        }

    }

}

@Composable
fun NoteItem(note: Note) {

    ConstraintLayout(
        modifier = Modifier
            .padding(start = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
    ) {
        val (time, sidebar, noteView) = createRefs()

        Text(
            text = getReadableDateTime(note.timeStamp).takeLast(8),
            modifier = Modifier.constrainAs(time) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            fontSize = 14.sp,
            color = LightGray,
            fontWeight = FontWeight.SemiBold
        )

        Box(
            Modifier
                .clip(shape = RoundedCornerShape(40.dp))
                .width(4.dp)
                .constrainAs(sidebar) {
                    top.linkTo(time.bottom, 5.dp)
                    start.linkTo(parent.start)
                    bottom.linkTo(noteView.bottom)
                    height = Dimension.fillToConstraints

                }
                .background(
                    LightGray.copy(0.2f)
                ))

        Box(modifier = Modifier
            .constrainAs(noteView) {
                top.linkTo(time.bottom, 10.dp)
                start.linkTo(sidebar.end, 20.dp)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
            .clip(RoundedCornerShape(30.dp))
            .background(Color(note.color)),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp, vertical = 18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = note.title,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )

                Text(
                    text = note.content,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Start,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                )

            }

        }
    }

}