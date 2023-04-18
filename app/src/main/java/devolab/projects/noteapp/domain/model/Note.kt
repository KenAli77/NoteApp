package devolab.projects.noteapp.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import androidx.room.PrimaryKey
import devolab.projects.noteapp.ui.theme.LightPurple
import devolab.projects.noteapp.ui.utils.getRandomColor

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val timeStamp: Long = System.currentTimeMillis(),
    val image: String? = null,
    val tag: List<String> = listOf("#all"),
    val color: Int = getRandomColor().toArgb()
)
