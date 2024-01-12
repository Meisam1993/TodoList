package com.example.todolist.data.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun getAllTasks(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task): Long

    @Update
    suspend fun editTask(task: Task): Int

    @Delete
    suspend fun deleteTask(task: Task): Int

    @Query("DELETE FROM task_table")
    suspend fun clearTasks()
}