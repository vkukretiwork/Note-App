package com.example.android.noteapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subtitle(
    var subtitleNote: String?,
    val idTitle: Int
){
    @PrimaryKey(autoGenerate = true) var idSubtitle: Int = 0
}