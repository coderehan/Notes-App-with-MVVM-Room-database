package com.rehan.notesappmvvm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rehan.notesappmvvm.R
import com.rehan.notesappmvvm.adapters.NotesAdapter
import com.rehan.notesappmvvm.databinding.ActivityMainBinding
import com.rehan.notesappmvvm.models.Note
import com.rehan.notesappmvvm.viewmodels.NotesViewModel

class MainActivity : AppCompatActivity(), NotesAdapter.NotesClicked {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NotesViewModel
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        notesAdapter = NotesAdapter(this, this)
        binding.rvNotes.adapter = notesAdapter

        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                notesAdapter.updateNotesList(it)
            }

        })

        binding.btnSave.setOnClickListener {
            validation()
        }

    }

    private fun validation() {
        val notes = binding.etNotes.text.toString().trim()

        if (TextUtils.isEmpty(notes)) {
            binding.etNotes.error = "Please write notes"
            binding.etNotes.requestFocus()
        } else {
            // If user is writing notes, we should insert it in table
            viewModel.insertNotes(Note(notes))
            Toast.makeText(this, "$notes Inserted successfully", Toast.LENGTH_LONG).show()
        }
    }

    override fun onNotesItemClicked(note: Note) {
        // On clicking delete button, we have to delete notes
        viewModel.deleteNotes(note)
        Toast.makeText(this, "${note.text} Deleted successfully", Toast.LENGTH_LONG).show()
    }
}