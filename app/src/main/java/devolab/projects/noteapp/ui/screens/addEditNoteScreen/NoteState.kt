package devolab.projects.noteapp.ui.screens.addEditNoteScreen

import androidx.compose.ui.graphics.toArgb
import devolab.projects.noteapp.ui.utils.getRandomColor

data class NoteState(
    val title:String = "",
    val content:String = "",
    val tags:ArrayList<String> = ArrayList<String>(),
    val color:Int = getRandomColor().toArgb(),
    val isTitleFocused:Boolean = false,
    val isContentFocused:Boolean = false,
    val titleHint:String = "enter a title..",
    val contentHing:String = "type in something..",
    val error:String? = null,
    val image:String?= null,
)
