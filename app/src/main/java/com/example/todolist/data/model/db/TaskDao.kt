package com.example.todolist.data.model.db

import androidx.room.Dao
import androidx.room.Query
import com.example.todolist.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun getAllTasks(): Flow<List<Task>>
}