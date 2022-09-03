package com.example.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todoTable")
class Todo(

    @ColumnInfo(name = "task")
    val task:String

) {

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

}