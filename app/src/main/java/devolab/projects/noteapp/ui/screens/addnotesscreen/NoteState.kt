package devolab.projects.noteapp.ui.screens.addnotesscreen

data class NoteState(
    val title:String = "",
    val content:String = "",
    val isTitleFocused:Boolean = false,
    val isContentFocused:Boolean = false,
    val titleHint:String = "enter a title..",
    val contentHing:String = "type in something..",
    val error:String? = null,
    val image:String?= null,
)
