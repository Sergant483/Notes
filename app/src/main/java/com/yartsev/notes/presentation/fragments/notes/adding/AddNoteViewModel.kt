package com.yartsev.notes.presentation.fragments.notes.adding

import androidx.lifecycle.ViewModel
import com.yartsev.notes.data.database.entity.NotesEntity
import com.yartsev.notes.data.repository.NotesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(val notesRepository: NotesRepositoryImpl) : ViewModel() {

    fun saveNote(note: NotesEntity) = notesRepository.insert(note)

    fun getNoteById(id: Int) = notesRepository.loadNoteById(id)

    fun deleteNoteById(id: Int) = notesRepository.deleteById(id)
}
