package com.example.todolist.data.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.data.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
}