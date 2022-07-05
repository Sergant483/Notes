package com.yartsev.notes.data.repository

import com.yartsev.notes.data.database.AppDataBase
import com.yartsev.notes.data.database.entity.TasksEntity
import javax.inject.Inject
//
//interface TasksRepository {
//    suspend fun getAllTasks(): List<TasksEntity>
//    suspend fun insertTask(task: TasksEntity)
//    suspend fun deleteById(id: Int)
//}
//
//class TasksRepositoryImpl @Inject constructor(private val db: AppDataBase) : TasksRepository {
//    override suspend fun getAllTasks(): List<TasksEntity> = db.tasksDao.getAll()
//
//    override suspend fun insertTask(task: TasksEntity) {
//        db.tasksDao.insert(task)
//    }
//
//    override suspend fun deleteById(id: Int) {
//        db.tasksDao.deleteById(id)
//    }
//
//}