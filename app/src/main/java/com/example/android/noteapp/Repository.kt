package com.example.android.noteapp

import androidx.lifecycle.LiveData

class Repository(private val titleDao : TitleDao) {

    val allTitles : LiveData<List<Title>> = titleDao.getAllTitles()

    suspend fun insertTitle(title : Title){
        titleDao.insertTitle(title)
    }
    suspend fun deleteTitle(title : Title){
        titleDao.deleteTitle(title)
    }
    suspend fun deleteAllTitles(){
        titleDao.deleteAllTitles()
    }


    val allTitlesWithSubtitles : LiveData<List<TitleWithSubtitles>> = titleDao.getTitlesWithSubtitles()

    suspend fun insertSubtitle(subtitle: Subtitle){
        titleDao.insertSubtitle(subtitle)
    }



//    fun getTitleWithSubtitles(titleNote : String) = titleDao.getTitleWithSubtitles(titleNote)

}