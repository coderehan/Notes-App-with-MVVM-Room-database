package com.rehan.notesappmvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rehan.notesappmvvm.dao.NotesDAO
import com.rehan.notesappmvvm.models.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun getNotesDao(): NotesDAO

    // Companion object is used to create single instance of room database
    companion object{
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        // Creating database instance
        fun getDatabaseInstance(context: Context) : NotesDatabase {
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, NotesDatabase::class.java, "notes_database").build()
                INSTANCE = instance
                instance
            }
        }

    }

}