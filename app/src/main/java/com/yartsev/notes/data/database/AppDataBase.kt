package com.yartsev.notes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yartsev.notes.data.database.dao.NotesDao
import com.yartsev.notes.data.database.entity.NotesEntity

private const val DB_VERSION = 1
private const val DB_NAME = "app.db"

@Database(entities = [NotesEntity::class], version = DB_VERSION)
abstract class AppDataBase : RoomDatabase() {
    abstract val movieDao: NotesDao

    companion object {
        fun newInstance(context: Context): AppDataBase =
            Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME).build()
    }
}