package com.example.notesapp

import androidx.lifecycle.LiveData

class NotesRepository(private val notesDao: NoteDao) {

    val getAllNotes : LiveData<List<Note>> = notesDao.getAllNotes()


    suspend fun insert(note: Note){
        notesDao.insert(note)
    }

    suspend fun update(note: Note){
        notesDao.update(note)
    }

    suspend fun delete(note: Note){
        notesDao.delete(note)
    }



}