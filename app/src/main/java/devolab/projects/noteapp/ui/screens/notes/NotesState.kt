package devolab.projects.noteapp.ui.screens.notes

import devolab.projects.noteapp.domain.model.Note

data class NotesState(
    val notes: List<Note> = emptyList(),
    val filters:List<String> = emptyList(),
    val tags:List<String> = emptyList(),
    val error:String? = null
)
