package com.yartsev.notes

import com.yartsev.notes.data.repository.NotesRepository
import com.yartsev.notes.data.repository.NotesRepositoryImpl
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    private val notesRepository = mock<NotesRepository>(NotesRepository::class.java)

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun repository_isCorrect() {
        notesRepository.loadAllNotes()
        verify(notesRepository, times(1)).loadAllNotes()
//        val notesList = listOf<NotesEntity>(
//            NotesEntity(25, "TEST_NOTE", "TEST_URI"),
//            NotesEntity(25, "TEST_NOTE", "TEST_URI")
//        )
//       val notesFlowable =  Observable.just(notesList).toFlowable(BackpressureStrategy.MISSING)
//       // val testNote = NotesEntity(25, "TEST_NOTE", "TEST_URI")
////        var addState = false
////        notesRepository.insert(testNote).subscribeOn(Schedulers.io())
////            .observeOn(AndroidSchedulers.mainThread()).subscribe { addState = true }
////        var lastNote = NotesEntity()
//        Mockito.`when`((db.movieDao.getAll())).thenReturn(notesFlowable)
//        notesRepository.loadAllNotes().subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread()).subscribe {  assertEquals(notesList, it) }

    }
}