package com.example.android.noteapp

import androidx.lifecycle.LiveData
import androidx.room.*

//@Dao
//interface TitleDao {
//    @Query("SELECT * FROM titles")
//    fun getAllTitles(): LiveData<List<Title>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(title: Title): Long
//
//    @Delete
//    suspend fun delete(title: Title)
//}