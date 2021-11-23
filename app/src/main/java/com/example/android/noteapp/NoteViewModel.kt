package com.example.android.noteapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes : LiveData<List<NoteTitle>>

    init {
        val dao = TitleDatabase.invoke(application).getTitleDao()
        val repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

}