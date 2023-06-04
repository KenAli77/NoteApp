package devolab.projects.noteapp.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import devolab.projects.noteapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note

    @Insert(onConflict = REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}