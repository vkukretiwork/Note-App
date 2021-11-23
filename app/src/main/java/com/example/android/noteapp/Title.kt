package com.example.android.noteapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(
//    tableName = "titles"
//)
data class Title(
//    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val id: Int,
    val title: String?
)