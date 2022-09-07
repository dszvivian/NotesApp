package com.example.notesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(
    entities = arrayOf(
        Note::class ,
        Todo::class
    ) ,
    version = 3 ,
    exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {



    abstract fun getNotesDao(): NoteDao
    abstract fun getTodoDao(): TodoDao

    companion object{

        @Volatile
        private var INSTANCE : NoteDatabase? = null


        //todo query not working
        private val migration_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {

                database.query("create table if not exists 'todoTable' ('task' TEXT , `id` INTEGER, PRIMARY KEY(`id`))")

            }

        }



        fun getDatabase(context : Context): NoteDatabase{

            return INSTANCE  ?: synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext ,
                    NoteDatabase::class.java,
                    "notes_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance

            }


        }




    }


}