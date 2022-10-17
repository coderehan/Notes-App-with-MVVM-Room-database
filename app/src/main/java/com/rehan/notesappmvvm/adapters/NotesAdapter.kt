package com.rehan.notesappmvvm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rehan.notesappmvvm.R
import com.rehan.notesappmvvm.models.Note

class NotesAdapter(private val context: Context, private val listener: NotesClicked): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_layout, parent, false))
        viewHolder.ivDelete.setOnClickListener {
            listener.onNotesItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNotes = allNotes[position]
        holder.tvNotes.text = currentNotes.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateNotesList(notesList: List<Note>){
        allNotes.clear()
        allNotes.addAll(notesList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNotes: TextView = itemView.findViewById(R.id.tvNotes)
        val ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)
    }

    interface NotesClicked{
        fun onNotesItemClicked(note: Note)
    }
}