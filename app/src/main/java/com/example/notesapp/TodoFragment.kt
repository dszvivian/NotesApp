package com.example.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoFragment : Fragment(R.layout.fragment_todo), OnTodoClick, OnTodoClickDelete {

    lateinit var rvTodo: RecyclerView
    lateinit var todoViewModel: NoteViewModel
    lateinit var btnTodoDone: TextView
    lateinit var adapter: TodoRVAdapter



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = activity

        rvTodo = view.findViewById(R.id.rvTodoList)



        rvTodo.layoutManager = LinearLayoutManager(context)

        adapter = TodoRVAdapter(context!!.applicationContext , this , this  )

        rvTodo.adapter = adapter

        todoViewModel = ViewModelProvider(this ,
            ViewModelProvider.AndroidViewModelFactory
                .getInstance(context!!.application))
            .get(NoteViewModel::class.java)

        todoViewModel.allTodo.observe(context!!, Observer { list ->
            list.let {
                adapter.updateTodo(it)
            }
        })



        val fabTodoAdd = view.findViewById<FloatingActionButton>(R.id.fabAddTodo)


        fabTodoAdd.setOnClickListener {

            val viewBSD :View = layoutInflater.inflate(R.layout.fragment_todobottomsheet , null)
            val dialog = BottomSheetDialog(requireContext())
            dialog.setContentView(viewBSD)
            dialog.show()

            btnTodoDone = viewBSD.findViewById(R.id.btnTodoDone)
            btnTodoDone.setOnClickListener{
                val etEnterTask = viewBSD.findViewById<TextView>(R.id.etEnterTask)
                val task = etEnterTask.text.toString()

                if(task.isNotEmpty()) {
                    todoViewModel.insertTodo(Todo(task,false))
                    Toast.makeText(getContext(), "New Task Added", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                else{
                    Toast.makeText(getContext(), "Some fields are empty ", Toast.LENGTH_SHORT).show()
                }

            }

        }

    }


    //update function if checkbox is clicked
    //todo refactor all the name of the interfaces
    override fun onTodoClick(todo: Todo) {
        val todoModel = Todo(task = todo.task ,isChecked = !todo.isChecked )
        todoModel.id = todo.id
        todoViewModel.updateTodo(todoModel)
        adapter.notifyDataSetChanged()
    }

    override fun onLongPressDelete(todo: Todo) {
        todoViewModel.deleteTodo(todo)
        Toast.makeText(context, "Task Deleted", Toast.LENGTH_SHORT).show()
    }


}