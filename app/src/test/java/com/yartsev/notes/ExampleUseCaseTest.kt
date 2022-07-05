package com.yartsev.notes

import com.yartsev.notes.data.database.entity.NotesEntity
import com.yartsev.notes.data.repository.NotesRepository
import com.yartsev.notes.data.usecases.GetAllNotesUseCase
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUseCaseTest {

    private lateinit var getAllNotesUseCase: GetAllNotesUseCase
    @Mock
    private lateinit var notesRepository: NotesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getAllNotesUseCase = GetAllNotesUseCase(notesRepository)
    }

    @Test
    fun repository_isCorrect() {

        val notesList = listOf<NotesEntity>(
            NotesEntity(3, "TEST_NOTE", "TEST_URI"),
            NotesEntity(3, "TEST_NOTE", "TEST_URI")
        )
        val notesFlowable = Observable.just(notesList).toFlowable(BackpressureStrategy.MISSING)
        `when`(notesRepository.loadAllNotes()).thenReturn(notesFlowable)

        var trololo = emptyList<NotesEntity>()
        getAllNotesUseCase.invoke().blockingSubscribe { trololo = it }
        assertEquals(notesList, trololo)
    }
}