package com.example.android.noteapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : Repository
    val allTitle : LiveData<List<Title>>
    val allTilesWithSubtitles : LiveData<List<TitlesWithSubtitles>>

    init {
        val dao = TitleDatabase.invoke(application).getTitleDao()
        repository = Repository(dao)
        allTitle = repository.allTitles
        allTilesWithSubtitles = repository.allTitlesWithSubtitles
    }


    fun deleteTitleAndItsSubtitles(title : Title) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTitleAndItsSubtitles(title)
    }

    fun insertTitle(title : Title) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertTitle(title)
    }
    fun deleteAllNotes() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllNotes()
    }

    fun insertSubtitle(subtitle: Subtitle) = viewModelScope.launch (Dispatchers.IO){
        repository.insertSubtitle(subtitle)
    }
    fun deleteSubtitle(subtitle: Subtitle) = viewModelScope.launch (Dispatchers.IO){
        repository.deleteSubtitle(subtitle)
    }


}