package com.example.android.noteapp

import androidx.lifecycle.LiveData

class Repository(private val titleDao : TitleDao) {

    val allTitles : LiveData<List<Title>> = titleDao.getAllTitles()

    suspend fun insertTitle(title : Title){
        titleDao.insertTitle(title)
    }

    suspend fun deleteAllNotes(){
        titleDao.deleteAllTitles()
        titleDao.deleteAllSubtitles()
    }
    suspend fun deleteTitleAndItsSubtitles(title : Title){
        titleDao.deleteTitle(title)
        titleDao.deleteSubtitleWithTitle(title.idTitle)
    }
    suspend fun updateTitle(title : Title){
     titleDao.updateTitle(title)
    }


    val allTitlesWithSubtitles : LiveData<List<TitlesWithSubtitles>> = titleDao.getTitlesWithSubtitles()

    suspend fun insertSubtitle(subtitle: Subtitle){
        titleDao.insertSubtitle(subtitle)
    }
    suspend fun deleteSubtitle(subtitle: Subtitle){
        titleDao.deleteSubtitle(subtitle)
    }
    suspend fun updateSubtitle(subtitle: Subtitle){
        titleDao.updateSubtitle(subtitle)
    }



//    fun getTitleWithSubtitles(titleNote : String) = titleDao.getTitleWithSubtitles(titleNote)

}