package com.example.android.noteapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Title(
    val titleNote: String?
){
    @PrimaryKey(autoGenerate = true) var idTitle: Int = 0
}