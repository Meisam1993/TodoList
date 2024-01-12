package com.example.todolist.data.repository

import com.example.todolist.data.model.Task
import com.example.todolist.data.model.db.TaskDao
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {
    override fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()

    override suspend fun addTask(task: Task): Long = taskDao.addTask(task)

    override suspend fun updateTask(task: Task): Int = taskDao.editTask(task)

    override suspend fun deleteTask(task: Task): Int = taskDao.deleteTask(task)

    override suspend fun clearTasks() = taskDao.clearTasks()

}