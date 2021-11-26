package com.yartsev.notes.data.usecases

import com.yartsev.notes.data.repository.NotesRepositoryImpl

class GetAllNotesUseCase(private val notesRepository: NotesRepositoryImpl) {
    suspend operator fun invoke() = notesRepository.loadAllNotes()
}