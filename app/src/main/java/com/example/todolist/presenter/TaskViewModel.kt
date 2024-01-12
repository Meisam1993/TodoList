package com.example.todolist.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.model.Task
import com.example.todolist.data.repository.TaskRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    private val _getAllTasksState: MutableLiveData<List<Task>> = MutableLiveData()
    val getAllTasksState: LiveData<List<Task>> = _getAllTasksState

    init {
        getAllTasks()
    }

    private fun getAllTasks() = viewModelScope.launch {
        taskRepository.getAllTasks().onEach {
            _getAllTasksState.postValue(it)
        }.collect()
    }
}