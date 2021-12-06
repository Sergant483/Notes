package com.yartsev.notes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yartsev.notes.data.database.entity.NotesEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface NotesDao {

    @Query("SELECT * FROM NotesEntity")
    fun getAll(): Flowable<List<NotesEntity>>

    @Query("SELECT * FROM NotesEntity WHERE id = :id")
    fun getNoteById(id: Int): Single<NotesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: NotesEntity): Completable

    @Query("DELETE  FROM NotesEntity WHERE id = :id")
    fun deleteById(id: Int):Completable

}