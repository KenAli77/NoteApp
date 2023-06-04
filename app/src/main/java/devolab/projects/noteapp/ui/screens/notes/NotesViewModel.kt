package devolab.projects.noteapp.ui.screens.notes

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import devolab.projects.noteapp.data.local.NotesRepositoryImpl
import devolab.projects.noteapp.domain.model.Note
import devolab.projects.noteapp.ui.utils.getReadableDateTime
import devolab.projects.noteapp.ui.utils.sampleNotes
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val repo: NotesRepositoryImpl) :
    ViewModel() {

    var notesState by mutableStateOf<NotesState>(NotesState())
        private set

    private var noteToDelete by mutableStateOf<Note>(Note())

    private var filters = mutableStateListOf<String>()

    init {
        getNotes()
    }

    private fun getNotes() = viewModelScope.launch {
        repo.getNotes().collect {
            val notes = it.sortedByDescending { it.timeStamp }

            val tags = ArrayList<String>()
            notes.forEach { note ->
                note.tag.forEach {
                    if (it.isNotEmpty()) {
                        tags.add(it)
                    }
                }
                Log.e("notes tags", note.tag.toString())
            }
            val tagSets = tags.toSet().toList()
            notesState = NotesState(notes = notes, tags = tagSets)

        }
    }


    fun groupNotesByDate(): Map<String, List<Note>> {
        val notes = notesState.filteredNotes.groupBy {
            val readableDate = getReadableDateTime(it.timeStamp)
            readableDate.take(readableDate.length - 8)
        }

        return notes
    }


    fun addFilter(filter: String) {
        filters.add(filter)
        notesState = notesState.copy(
            filters = ArrayList(filters),
            filteredNotes = notesState.notes.filter { it.tag.containsAll(filters) }
        )
    }

    fun removeFilter(filter: String) {
        filters.remove(filter)
        notesState = notesState.copy(
            filters = ArrayList(filters),
            filteredNotes = notesState.notes.filter { it.tag.containsAll(filters) },
        )
    }

    fun showDeleteDialog(note: Note) {
        notesState = notesState.copy(
            isDialogShowing = true
        )
        noteToDelete = note
    }

    fun dismissDeleteDialog() {
        notesState = notesState.copy(
            isDialogShowing = false
        )
        noteToDelete = Note()
    }

    fun deleteNote() = viewModelScope.launch {
        repo.deleteNote(noteToDelete)
    }

}