package com.example.notesapp

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CheckedTextView
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class TodoRVAdapter(

    val context: Context,
    val onTodoClickInterface: OnTodoClick,
    val onTodoClickDelete: OnTodoClickDelete,

    ) : RecyclerView.Adapter<TodoRVAdapter.ViewHolder>() {

    val allTodo = ArrayList<Todo>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val task = view.findViewById<TextView>(R.id.tvTodo)
        val cbTodo = view.findViewById<CheckBox>(R.id.cbTodo)
        val btnCancel = view.findViewById<ImageButton>(R.id.btnTodoCancel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.todo_rv_item,
            parent,
            false
        )

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.task.setText(allTodo.get(position).task)




        holder.btnCancel.setOnClickListener {
            onTodoClickDelete.onLongPressDelete(todo = allTodo.get(position))
        }

        holder.cbTodo.setOnClickListener {
            onTodoClickInterface.onTodoClick(allTodo.get(position))
        }

        if (allTodo.get(position).isChecked) {
            holder.cbTodo!!.isChecked = true
            holder.cbTodo.jumpDrawablesToCurrentState()
            holder.task.paintFlags = holder.task.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.task.setTextColor(Color.LTGRAY)
        } else {
            holder.cbTodo!!.isChecked = false
            holder.cbTodo.jumpDrawablesToCurrentState()
            holder.task.setTextColor(Color.BLACK)
            holder.task.paintFlags = 0
        }

    }

    override fun getItemCount(): Int {
        return allTodo.size
    }


    fun updateTodo(newList: List<Todo>) {
        allTodo.clear()
        allTodo.addAll(newList)
        notifyDataSetChanged()
    }


}


interface OnTodoClick {
    fun onTodoClick(todo: Todo)
}


interface OnTodoClickDelete {
    fun onLongPressDelete(todo: Todo)
}
