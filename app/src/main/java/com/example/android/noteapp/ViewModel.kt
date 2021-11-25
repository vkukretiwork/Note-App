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
    val allTilesWithSubtitles : LiveData<List<TitleWithSubtitles>>

    init {
        val dao = TitleDatabase.invoke(application).getTitleDao()
        repository = Repository(dao)
        allTitle = repository.allTitles
        allTilesWithSubtitles = repository.allTitlesWithSubtitles
    }

    fun deleteTitle(title : Title) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteTitle(title)
    }

    fun insertTitle(title : Title) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertTitle(title)
    }
    fun deleteAllTitles() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllTitles()
    }


    fun insertSubtitle(subtitle: Subtitle) = viewModelScope.launch (Dispatchers.IO){
        repository.insertSubtitle(subtitle)
    }


//    fun getTitleWithSubtitles(tileNote : String) = repository.getTitleWithSubtitles(tileNote)

}