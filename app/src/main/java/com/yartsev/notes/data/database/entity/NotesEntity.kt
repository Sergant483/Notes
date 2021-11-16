package com.yartsev.notes.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    val Id:Int = 0,
    val text:String = "",
    val data:Long = 0L,

)