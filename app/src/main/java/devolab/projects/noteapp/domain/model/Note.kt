package devolab.projects.noteapp.domain.model

import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import androidx.room.PrimaryKey
import devolab.projects.noteapp.ui.utils.getRandomColor

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String="",
    val content: String="",
    val timeStamp: Long = System.currentTimeMillis(),
    val tag: List<String> = ArrayList<String>(),
    val color: Int = getRandomColor().toArgb()
)
