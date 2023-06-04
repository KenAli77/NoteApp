package devolab.projects.noteapp.data.local

import devolab.projects.noteapp.domain.model.Note
import devolab.projects.noteapp.domain.repository.NotesRepository
import devolab.projects.noteapp.ui.utils.sampleNotes
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepositoryImpl @Inject constructor(private val dao: NotesDao) : NotesRepository {

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: NotesRepositoryImpl? = null

        fun getInstance(notesDao: NotesDao) =
            instance ?: synchronized(this) {
                instance ?: NotesRepositoryImpl(notesDao).also { instance = it }
            }
    }

    init {

    }

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

}