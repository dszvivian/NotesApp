package com.example.notesapp

import androidx.lifecycle.LiveData

class NotesRepository(private val notesDao: NoteDao,private val todoDao: TodoDao) {

    val getAllNotes : LiveData<List<Note>> = notesDao.getAllNotes()
    val getAllTodo : LiveData<List<Todo>> = todoDao.getAllTodo()


    suspend fun insert(note: Note){
        notesDao.insert(note)
    }

    suspend fun update(note: Note){
        notesDao.update(note)
    }

    suspend fun delete(note: Note){
        notesDao.delete(note)
    }



    //todo_functions
    suspend fun insertTodo(todo: Todo){
        todoDao.insert(todo)
    }

    suspend fun updateTodo(todo: Todo){
        todoDao.update(todo)
    }

    suspend fun deleteTodo(todo: Todo){
        todoDao.delete(todo)
    }






}