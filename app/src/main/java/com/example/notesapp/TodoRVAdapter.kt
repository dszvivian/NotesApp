package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoRVAdapter(

    val context: Context,
    val onTodoClickInterface: OnTodoClick ,
    val onTodoClickDelete: OnTodoClickDelete

): RecyclerView.Adapter<TodoRVAdapter.ViewHolder>() {

    val allTodo = ArrayList<Todo>()


    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val task = view.findViewById<TextView>(R.id.tvTodo)
        val cbTodo = view.findViewById<CheckBox>(R.id.cbTodo)
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

        holder.itemView.setOnLongClickListener{
            onTodoClickDelete.onLongPressDelete(todo = allTodo.get(position))
            false
        }

        holder.task.setOnClickListener{
            onTodoClickInterface.onTodoClick(allTodo.get(position))
        }


    }

    override fun getItemCount(): Int {
        return allTodo.size
    }


    fun updateTodo(newList:List<Todo>){
        allTodo.clear()
        allTodo.addAll(newList)
        notifyDataSetChanged()
    }


}


interface OnTodoClick{
    fun onTodoClick(todo: Todo)
}


interface OnTodoClickDelete{
    fun onLongPressDelete(todo: Todo)
}
