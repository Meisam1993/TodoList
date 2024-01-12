package com.example.todolist.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todolist.data.utils.Constants.Companion.TASK_TABLE_NAME

@Entity(tableName = TASK_TABLE_NAME)
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = 0,
    var title: String,
    @ColumnInfo(name = "completed")
    var isCompleted: Boolean = false
)
