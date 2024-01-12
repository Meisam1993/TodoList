package com.example.todolist.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.model.Task
import com.example.todolist.presenter.dialogs.AddTaskDialog
import com.example.todolist.presenter.dialogs.AddTaskDialogCallback
import com.example.todolist.presenter.dialogs.EditTaskDialog
import com.example.todolist.presenter.dialogs.EditTaskDialogCallback
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), AddTaskDialogCallback, OnTaskItemEventListener,
    EditTaskDialogCallback {
    private val viewModel: TaskViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private val taskAdapter = TaskAdapter(this)
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

        viewModel.addTaskState.observe(this, Observer {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        })

        viewModel.editTaskState.observe(this, Observer {
            taskAdapter.editTask(it)
        })

        viewModel.deleteTaskState.observe(this, Observer {
            taskAdapter.deleteTask(it)
        })

        viewModel.clearTasksState.observe(this, Observer {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        })

        viewModel.searchInTasksState.observe(this, Observer {
            taskAdapter.setSearchTasks(it)
        })
    }

    private fun setupView() {
        recyclerView = findViewById<RecyclerView?>(R.id.tasks_rv).apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = taskAdapter
        }

        val addTaskBtn = findViewById<FloatingActionButton>(R.id.add_task_btn)
        addTaskBtn.setOnClickListener {
            AddTaskDialog(this).show(supportFragmentManager, null)
        }

        val clearBtn = findViewById<ImageView>(R.id.clear_btn)
        clearBtn.setOnClickListener {
            viewModel.clearTasks()
        }

        val searchEt = findViewById<EditText>(R.id.search_bar)
        searchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.searchInTasks(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    override fun onNewTask(task: Task) {
        viewModel.addTask(task)
    }

    override fun onTaskItemLongClick(task: Task) {
        EditTaskDialog(this, task).show(supportFragmentManager, null)
    }

    override fun onDeleteButtonClick(task: Task) {
        viewModel.deleteTask(task)
    }

    override fun onItemCheckedChange(task: Task) {
        viewModel.editTask(task)
    }

    override fun onEditTask(task: Task) {
        viewModel.editTask(task)
    }
}