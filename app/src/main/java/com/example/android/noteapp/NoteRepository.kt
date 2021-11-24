package com.example.android.noteapp

import android.provider.ContactsContract
import androidx.lifecycle.LiveData

class NoteRepository(private val titleDao : TitleDao) {

    val allNotes : LiveData<List<NoteTitle>> = titleDao.getAllTitles()

    suspend fun insert(title : NoteTitle){
        titleDao.insert(title)
    }
    suspend fun delete(title : NoteTitle){
        titleDao.delete(title)
    }
    suspend fun deleteAll(){
        titleDao.deleteAll()
    }
}