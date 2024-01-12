package com.example.todolist.data.repository

import com.example.todolist.data.model.Task
import com.example.todolist.data.model.db.TaskDao
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {
    override fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()

}