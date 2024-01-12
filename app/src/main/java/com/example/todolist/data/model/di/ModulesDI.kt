package com.example.todolist.data.model.di

import com.example.todolist.data.model.db.TaskDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModules = module {
    single { provideTaskDatabase(androidContext()) }
    single { provideTaskDao(get()) }
}