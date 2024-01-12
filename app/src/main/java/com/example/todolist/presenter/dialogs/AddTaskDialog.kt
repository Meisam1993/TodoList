package com.example.todolist.presenter.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.todolist.R
import com.example.todolist.data.model.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddTaskDialog(val callback: AddTaskDialogCallback) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_add_task, null, false)
        val taskBarLayout = view.findViewById<TextInputLayout>(R.id.dialog_add_TIL)
        val taskEt = view.findViewById<TextInputEditText>(R.id.dialog_add_TIET)
        val saveBtn = view.findViewById<Button>(R.id.dialog_add_save_btn)
        val cancelBtn = view.findViewById<Button>(R.id.dialog_add_cancel_btn)

        saveBtn.setOnClickListener {
            if (taskEt.length() > 0) {
                val task = Task(
                    title = taskEt.text.toString()
                )
                callback.onNewTask(task)
                dismiss()
            } else {
                taskBarLayout.error = "لطفا جای خالی را پر کنید"
            }
        }

        cancelBtn.setOnClickListener {
            dismiss()
        }
        return AlertDialog.Builder(requireContext())
            .setView(view)
            .create()
    }
}