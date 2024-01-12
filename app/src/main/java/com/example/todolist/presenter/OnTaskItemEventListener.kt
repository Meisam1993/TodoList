package com.example.todolist.presenter

import com.example.todolist.data.model.Task

interface OnTaskItemEventListener {
    fun onTaskItemLongClick(task: Task)

    fun onDeleteButtonClick(task: Task)
}