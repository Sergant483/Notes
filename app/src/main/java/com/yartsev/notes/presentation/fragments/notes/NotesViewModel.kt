package com.yartsev.notes.presentation.fragments.notes

import androidx.lifecycle.*
import com.yartsev.notes.data.database.entity.NotesEntity
import com.yartsev.notes.data.repository.NotesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val notesRepository: NotesRepositoryImpl) :
    ViewModel() {
    private val _notes = MutableLiveData<List<NotesEntity>>()
    val notes: LiveData<List<NotesEntity>> = _notes

    fun getAllNotes() {
        notesRepository.loadAllNotes().observeOn(AndroidSchedulers.mainThread())
            .subscribe { notes ->
                _notes.value = notes
            }
    }

    fun deleteNote(id: Int) = notesRepository.deleteById(id = id)

}