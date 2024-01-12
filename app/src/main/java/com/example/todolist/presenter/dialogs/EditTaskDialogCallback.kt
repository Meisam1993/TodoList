package com.example.todolist.presenter.dialogs

import com.example.todolist.data.model.Task

interface EditTaskDialogCallback {
    fun onEditTask(task: Task)
}