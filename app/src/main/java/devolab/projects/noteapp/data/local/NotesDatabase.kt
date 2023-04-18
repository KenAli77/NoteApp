package devolab.projects.noteapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import devolab.projects.noteapp.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {

        const val DATABASE_NAME = "notes_database"

        // For Singleton instantiation
        // We instantiate the database using a singleton pattern.
        // This allows us to have only one instance running at any given point
        // This helps avoid unnecessary object instantiations and it's a way to optimize
        // Resource utilization

        @Volatile
        private var instance: NotesDatabase? = null

        fun getInstance(context: Context): NotesDatabase {
            return instance ?: synchronized(this) {
                // "also" could be read as : also do this for it...
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): NotesDatabase {
            return  Room.databaseBuilder(
                context,
                NotesDatabase::class.java,
                DATABASE_NAME
            ).build()
        }

    }


}