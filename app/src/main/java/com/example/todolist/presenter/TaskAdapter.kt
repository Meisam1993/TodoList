package com.example.todolist.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.model.Task

class TaskAdapter(val listener: OnTaskItemEventListener) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val taskList: MutableList<Task> = mutableListOf()

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val checkbox = itemView.findViewById<CheckBox>(R.id.task_checkbox)
        private val deleteBtn = itemView.findViewById<ImageView>(R.id.delete_task_btn)

        fun bindTask(task: Task) {
            checkbox.isChecked = task.isCompleted
            checkbox.text = task.title
            deleteBtn.setOnClickListener {
                listener.onDeleteButtonClick(task)
            }
            itemView.setOnLongClickListener {
                listener.onTaskItemLongClick(task)
                true
            }
        }
    }

    fun addTasks(taskList: List<Task>) {
        this.taskList.clear()
        this.taskList.addAll(taskList)
        notifyDataSetChanged()
    }

    fun editTask(task: Task) {
        for (i in 0 until taskList.size) {
            if (taskList[i].id == task.id) {
                taskList[i] = task
                notifyItemChanged(i)
                break
            }
        }
    }

    fun deleteTask(task: Task) {
        for (i in 0 until taskList.size) {
            if (taskList[i].id == task.id) {
                taskList[i] = task
                notifyItemRemoved(i)
                break
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = taskList.size


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindTask(taskList[position])
    }
}