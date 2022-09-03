package com.example.notesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = arrayOf(
        Note::class ,
        Todo::class
    ) ,
    version = 2 ,
    exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {



    abstract fun getNotesDao(): NoteDao
    abstract fun getTodoDao(): TodoDao



    companion object{

        @Volatile
        private var INSTANCE : NoteDatabase? = null



        fun getDatabase(context : Context): NoteDatabase{

            return INSTANCE  ?: synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext ,
                    NoteDatabase::class.java,
                    "notes_database"
                ).build()
                INSTANCE = instance


                instance

            }


        }




    }


}