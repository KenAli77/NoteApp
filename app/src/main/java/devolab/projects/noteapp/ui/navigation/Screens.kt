package devolab.projects.noteapp.ui.navigation


sealed class Screens(val route:String,val title:String){

    object NotesScreen: Screens(route = "notes_screen", title = "notes")
    object AddEditNoteScreen: Screens(route = "add_edit_note_screen/noteId", title = "add note")

}