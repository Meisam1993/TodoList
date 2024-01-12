package com.example.todolist

import android.app.Application
import com.example.todolist.data.model.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            /** Context **/
            /** Context **/
            androidContext(this@App)
            /** Room Database **/
            /** Room Database **/
            modules(appModules)
        }
    }
}