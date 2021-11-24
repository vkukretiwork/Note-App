package com.example.android.noteapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : NoteRepository
    val allNotes : LiveData<List<NoteTitle>>

    init {
        val dao = TitleDatabase.invoke(application).getTitleDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(title : NoteTitle) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(title)
    }

    fun insertNote(title : NoteTitle) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(title)
    }
    fun deleteAllNote() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

}