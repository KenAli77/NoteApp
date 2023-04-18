package devolab.projects.noteapp.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import devolab.projects.noteapp.data.local.NotesRepositoryImpl
import devolab.projects.noteapp.domain.model.Note
import devolab.projects.noteapp.ui.screens.addnotesscreen.NoteState
import devolab.projects.noteapp.ui.screens.notes.NotesState
import devolab.projects.noteapp.ui.utils.getReadableDateTime
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(val repo: NotesRepositoryImpl) :
    ViewModel() {

    // TODO filter notes by tag; select and deselect tags and show notes accordingly

    // TODO group notes by date and determine date nomenclature

    // TODO validate note insertion

    // TODO CRUD operations on db

    var notesState by mutableStateOf<NotesState>(NotesState())

    var noteState by mutableStateOf<NoteState>(NoteState(title = "", content = ""))
        private set
    val filters = mutableStateListOf<String>()

    init {
        getNotes()
        getAllTags()
    }

    private fun getNotes() = viewModelScope.launch {
        repo.getNotes().collect {

            notesState = NotesState(notes = it)

        }
    }

    fun groupNotesByDate(): Map<String, List<Note>> {
        val notes = notesState.notes.groupBy {
            val readableDate = getReadableDateTime(it.timeStamp)
            readableDate.take(readableDate.length - 8)
        }

        return notes
    }

    fun getAllTags(){
        val tags = ArrayList<String>()
        notesState.notes.forEach {
           it.tag.forEach{ tag ->
               if(!tags.contains(tag)) {
                   tags.add(tag)
               }
           }
        }
    }

    fun addFilter(filter: String) {
        filters.add(filter)
    }

    fun removeFilter(filter: String) {
        filters.remove(filter)
    }

    fun changeContentFocus(isFocused: Boolean) {
        noteState = noteState.copy(
            isContentFocused = isFocused
        )
    }

    fun changeTitleFocus(isFocused: Boolean) {
        noteState = noteState.copy(
            isTitleFocused = isFocused
        )
    }

    fun changeNoteContent(content: String) {
        noteState = noteState.copy(
            content = content
        )
    }

    fun changeNoteTitle(title: String) {
        noteState = noteState.copy(
            title = title
        )
    }

    private fun applyFilters(tags: List<String>) = viewModelScope.launch {
        // notesState.filter { it.tag?.containsAll(tags) == true }
    }

    fun insertNote() = viewModelScope.launch {
        val note = Note(
            title = noteState.title,
            content = noteState.content,
            timeStamp = System.currentTimeMillis(),
            image = noteState.image
        )
        if (note.content.isNotEmpty() && note.title.isNotEmpty()) {
            repo.insertNote(note)
            noteState = NoteState()
        }
    }


}