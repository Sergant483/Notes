package com.yartsev.notes.data.usecases

import com.yartsev.notes.data.repository.NotesRepository

class GetAllNotesUseCase(private val notesRepository: NotesRepository) {
    operator fun invoke() = notesRepository.loadAllNotes()//.filter { it.get(it.size-1).Id > 2 }
}