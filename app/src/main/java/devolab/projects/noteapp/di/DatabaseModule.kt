package devolab.projects.noteapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import devolab.projects.noteapp.data.local.NotesDao
import devolab.projects.noteapp.data.local.NotesDatabase
import devolab.projects.noteapp.ui.utils.sampleNotes
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object DatabaseModule {

    @Singleton
    @Provides
    fun provideNotesDatabase(@ApplicationContext context: Context): NotesDatabase {
        return NotesDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideNotesDao(notesDb: NotesDatabase): NotesDao {
//        CoroutineScope(Dispatchers.IO).launch {
//            insertDefaultNotes(notesDb)
//        }
        return notesDb.notesDao()
    }

    private suspend fun insertDefaultNotes(db:NotesDatabase){
        db.notesDao().getNotes().collect{
            if(it.isEmpty()){
                sampleNotes.forEach {
                    db.notesDao().insertNote(it)
                }
            }
        }
    }
}