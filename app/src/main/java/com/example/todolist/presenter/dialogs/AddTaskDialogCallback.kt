package com.example.todolist.presenter.dialogs

import com.example.todolist.data.model.Task

interface AddTaskDialogCallback {
    fun onNewTask(task: Task)
}