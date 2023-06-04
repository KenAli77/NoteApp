package devolab.projects.noteapp.ui.screens.addEditNoteScreen

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import devolab.projects.noteapp.data.local.NotesRepositoryImpl
import devolab.projects.noteapp.domain.model.Note
import devolab.projects.noteapp.ui.utils.sampleNotes
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: NotesRepositoryImpl
) : ViewModel() {

    private var noteId: Int? = savedStateHandle["noteId"]

    var noteState by mutableStateOf<NoteState>(NoteState(title = "", content = ""))
        private set

    var tags = mutableStateListOf<String>()

    var tag by mutableStateOf("")

    init {

        Log.e(this.javaClass.simpleName, "note id: $noteId")

        savedStateHandle.get<Int>("noteId")?.let { id ->
            Log.e("stateHandle", id.toString())
            if (id != -1) {
                viewModelScope.launch {
                    repo.getNoteById(id).also { note ->
                        noteId = note.id
                        noteState = NoteState(
                            title = note.title,
                            content = note.content,
                            tags = ArrayList(note.tag),
                            color = note.color
                        )
                        tags = note.tag.toMutableStateList()

                    }

                }
            }
        }

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

    fun changeNoteColor(color: Int) {
        noteState = noteState.copy(
            color = color
        )
    }

    fun changeNoteTitle(title: String) {
        noteState = noteState.copy(
            title = title
        )
    }

    fun addTag(tag: String) {
        tags.add(tag)
        noteState = noteState.copy(
            tags = ArrayList(tags)
        )
        this.tag = ""
    }

    fun removeTag(tag: String) {
        tags.remove(tag)
        noteState = noteState.copy(
            tags = ArrayList(tags)
        )

    }

    fun insertNote() = viewModelScope.launch {

        var note = Note(
            title = noteState.title,
            content = noteState.content,
            timeStamp = System.currentTimeMillis(),
            tag = noteState.tags,
            color = noteState.color
        )

        if (noteId != -1) {
            note = note.copy(
                id = noteId!!,
            )
        }

        if (note.content.isNotEmpty() && note.title.isNotEmpty()) {
            Log.e("note tags: ", note.tag.toString())
            repo.insertNote(note)
            noteState = NoteState()
        }
    }


}