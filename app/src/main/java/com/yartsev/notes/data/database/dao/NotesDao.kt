package com.yartsev.notes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yartsev.notes.data.database.entity.NotesEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface NotesDao {

    @Query("SELECT * FROM NotesEntity")
    fun getAll(): Flowable<List<NotesEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: NotesEntity):Completable

    @Query("DELETE  FROM NotesEntity WHERE id = :id")
    fun deleteById(id: Int):Completable

}