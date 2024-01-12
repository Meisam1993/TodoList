package com.example.todolist.data.model.di

import android.content.Context
import androidx.room.Room
import com.example.todolist.data.model.db.TaskDatabase
import com.example.todolist.data.utils.Constants

fun provideTaskDatabase(context: Context) =
    Room.databaseBuilder(context, TaskDatabase::class.java, Constants.TASK_DATABASE_NAME)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun provideTaskDao(taskDatabase: TaskDatabase) = taskDatabase.getTaskDao()