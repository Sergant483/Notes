package com.yartsev.notes.data.repository

import com.yartsev.notes.data.database.AppDataBase
import com.yartsev.notes.data.database.entity.NotesEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject


interface NotesRepository {
    fun loadAllNotes(): Flowable<List<NotesEntity>>
    fun insert(note: NotesEntity): Completable
    fun deleteById(id: Int): Completable
}

class NotesRepositoryImpl @Inject constructor(private val db: AppDataBase) : NotesRepository {

    override fun loadAllNotes(): Flowable<List<NotesEntity>> = db.movieDao.getAll()

    override fun insert(note: NotesEntity) = db.movieDao.insert(note)

    override fun deleteById(id: Int) = db.movieDao.deleteById(id)

}
