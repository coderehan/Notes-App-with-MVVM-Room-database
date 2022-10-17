package com.rehan.notesappmvvm.repositories

import androidx.lifecycle.LiveData
import com.rehan.notesappmvvm.dao.NotesDAO
import com.rehan.notesappmvvm.models.Note

class NotesRepository(private val notesDAO: NotesDAO) {

    // Getting all notes from DAO and storing in LiveData object
    val allNotes: LiveData<List<Note>> = notesDAO.getAllNotes()

    suspend fun insert(note: Note) {
        notesDAO.insert(note)
    }

    suspend fun delete(note: Note) {
        notesDAO.delete(note)
    }
}