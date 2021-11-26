package com.yartsev.notes.presentation.fragments.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yartsev.notes.data.database.entity.NotesEntity
import com.yartsev.notes.databinding.NotesItemBinding

typealias OnItemClickListener = (position: Int) -> Unit

class NotesAdapter(onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private var notesItems = mutableListOf<NotesEntity>()

    fun setNotesList(notes: List<NotesEntity>) {
        this.notesItems = notes.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            NotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(notesItems[position])
    }

    override fun getItemCount(): Int = notesItems.size

    inner class NotesViewHolder(private val binding: NotesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteItem: NotesEntity) {
            binding.text.text = noteItem.text
            Glide.with(binding.image.context).load(noteItem.imageUri.toUri()).centerCrop()
                .into(binding.image)
        }
    }

}