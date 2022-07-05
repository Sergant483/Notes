package com.yartsev.notes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yartsev.notes.data.database.entity.TasksEntity

@Dao
interface TasksDao {

    @Query("SELECT * FROM TasksEntity")
    suspend fun getAll(): List<TasksEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TasksEntity)

    @Query("DELETE  FROM TasksEntity WHERE id = :id")
    suspend fun deleteById(id: Int)

}