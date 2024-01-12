package com.example.todolist.data.model.di

import com.example.todolist.data.repository.TaskRepository
import com.example.todolist.data.repository.TaskRepositoryImpl
import com.example.todolist.presenter.TaskViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single { provideTaskDatabase(androidContext()) }
    single { provideTaskDao(get()) }
    factory<TaskRepository> { TaskRepositoryImpl(get()) }
    viewModel {TaskViewModel(get())}
}