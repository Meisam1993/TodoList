package com.example.todolist.data.model

data class Task(
    var id: Long? = 0,
    var title: String,
    var isCompleted: Boolean = false
)
