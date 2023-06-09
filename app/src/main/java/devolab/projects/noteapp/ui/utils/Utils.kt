package devolab.projects.noteapp.ui.utils

import androidx.compose.foundation.ScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp
import devolab.projects.noteapp.domain.model.Note
import devolab.projects.noteapp.ui.theme.LightPurple
import devolab.projects.noteapp.ui.theme.PastelBlue
import devolab.projects.noteapp.ui.theme.PastelGreen
import devolab.projects.noteapp.ui.theme.Peach
import java.text.SimpleDateFormat
import java.util.*

const val HORIZONTAL_MAIN_PADDING = 12

const val LOREM_IPSUM =
    "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat"

fun getReadableDateTime(timestamp: Long): String {
    val date = Date(timestamp)
    val formatter = SimpleDateFormat("EEEE, MMMM dd yyyy hh:mm a", Locale.US)
    return formatter.format(date)

}

fun getRandomColor(): Color {
    val colors = listOf(
        LightPurple,
        Peach,
        PastelGreen,
        PastelBlue
    )
    return colors.random()
}

val sampleNotes:List<Note> = listOf(
    Note(
        id = 0,
        title = "I love pizza",
        content = "Pizza is the best thing, i wish i could have it every day!!",
        timeStamp = 1682937000000,
        tag = listOf("yummy","food")
    ),
    Note(
        id = 1,
        title = "how cool is that",
        content = LOREM_IPSUM,
        timeStamp = 1643501904000L,
        tag = listOf("cool")
    ),
    Note(
        id = 2,
        title = "just another day",
        content = LOREM_IPSUM,
        timeStamp = 1643588304000L,
        tag = listOf("boring")
    ),
    Note(
        id = 3,
        title = "the best time ever!",
        content = LOREM_IPSUM,
        timeStamp = 1643069904000L,
        tag = listOf("happy")
    ),
    Note(
        id = 4,
        title = "i need a kitten in my life",
        content = LOREM_IPSUM,
        timeStamp = 1643156304000L,
        tag = listOf("cats")
    )

)





