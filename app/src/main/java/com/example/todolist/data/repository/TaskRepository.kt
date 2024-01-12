package com.example.todolist.data.repository

import com.example.todolist.data.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getAllTasks(): Flow<List<Task>>

    suspend fun addTask(task: Task): Long
}