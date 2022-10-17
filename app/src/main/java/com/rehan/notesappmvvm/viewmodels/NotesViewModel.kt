package com.rehan.notesappmvvm.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.rehan.notesappmvvm.database.NotesDatabase
import com.rehan.notesappmvvm.models.Note
import com.rehan.notesappmvvm.repositories.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotesRepository
    val allNotes: LiveData<List<Note>>

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).getNotesDao()
        repository = NotesRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNotes(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNotes(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}