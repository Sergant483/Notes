package com.yartsev.notes.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yartsev.notes.data.repository.NotesRepository

@Entity
data class TasksEntity(
    @PrimaryKey(autoGenerate = true)
    val Id:Int
)