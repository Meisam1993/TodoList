package com.example.todolist.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.model.Task
import com.example.todolist.data.repository.TaskRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    private val _getAllTasksState: MutableLiveData<List<Task>> = MutableLiveData()
    val getAllTasksState: LiveData<List<Task>> = _getAllTasksState

    private val _addTaskState: MutableLiveData<String> = MutableLiveData()
    val addTaskState: LiveData<String> = _addTaskState

    private val _editTaskState: MutableLiveData<Task> = MutableLiveData()
    val editTaskState: LiveData<Task> = _editTaskState

    private val _deleteTaskState: MutableLiveData<Task> = MutableLiveData()
    val deleteTaskState: LiveData<Task> = _deleteTaskState

    private val _clearTasksState: MutableLiveData<String> = MutableLiveData()
    val clearTasksState: LiveData<String> = _clearTasksState

    private val _searchInTasksState: MutableLiveData<List<Task>> = MutableLiveData()
    val searchInTasksState: LiveData<List<Task>> = _searchInTasksState

    init {
        getAllTasks()
    }

    private fun getAllTasks() = viewModelScope.launch {
        taskRepository.getAllTasks().onEach {
            _getAllTasksState.postValue(it)
        }.collect()
    }

    fun addTask(task: Task) = viewModelScope.launch {
        val result = taskRepository.addTask(task)
        if (result == -1L) {
            _addTaskState.postValue("Task not added")
        } else {
            _addTaskState.postValue("Task added successfully")
        }
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        val result = taskRepository.deleteTask(task)
        if (result != -1) {
            _deleteTaskState.value = task
        }
    }

    fun editTask(task: Task) = viewModelScope.launch {
        val result = taskRepository.updateTask(task)
        if (result != -1) {
            _editTaskState.value = task
        }
    }

    fun clearTasks() = viewModelScope.launch {
        taskRepository.clearTasks()
        val result = taskRepository.getAllTasks().first()
        if (result.isNotEmpty()) {
            _clearTasksState.postValue("Tasks Not cleared")
        } else {
            _clearTasksState.postValue("Tasks cleared successfully")
        }
    }

    fun searchInTasks(query: String) = viewModelScope.launch {
        if (query != "") {
             taskRepository.searchInTasks(query).onEach {

                 _searchInTasksState.value = it
             }.collect()
        } else {
            getAllTasks()
        }
    }
}