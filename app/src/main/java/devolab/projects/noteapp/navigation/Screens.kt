package devolab.projects.noteapp.navigation

const val MAIN_ROUTE = "main_route"

sealed class Screens(val route:String,val title:String){

    object NotesScreen:Screens(route = "notes_screen", title = "notes")
    object AddNoteScreen:Screens(route = "add_note_screen", title = "add note")
    object NoteDetailsScreen:Screens(route = "note_details", title = "note details")

}