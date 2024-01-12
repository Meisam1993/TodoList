package com.example.todolist.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: TaskViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private val taskAdapter = TaskAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        observe()
    }

    private fun observe() {
        viewModel.getAllTasksState.observe(this, Observer {
            taskAdapter.addTasks(it)
        })
    }

    private fun setupView() {
        recyclerView = findViewById<RecyclerView?>(R.id.tasks_rv).apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = taskAdapter
        }
    }
}