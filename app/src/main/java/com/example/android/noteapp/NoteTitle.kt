package com.example.android.noteapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "title_notes_table"
)
data class NoteTitle(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val title: String?
)