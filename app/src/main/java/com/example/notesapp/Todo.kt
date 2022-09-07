package com.example.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todoTable")
class Todo(
    @ColumnInfo(name = "task")
    val task:String ,

    @ColumnInfo(name =  "isChecked")
    var isChecked:Boolean

) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}