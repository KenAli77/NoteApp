package devolab.projects.noteapp.ui.screens.notes

import devolab.projects.noteapp.domain.model.Note

data class NotesState(
    val notes: List<Note> = emptyList(),
    val filters:ArrayList<String> = ArrayList<String>(),
    val filteredNotes:List<Note> = notes.filter { it.tag?.containsAll(filters) ?: true },
    var tags:List<String> = ArrayList<String>(),
    val isDialogShowing:Boolean = false,
    val noteToDelete:Note? = null,
    val error:String? = null
)
