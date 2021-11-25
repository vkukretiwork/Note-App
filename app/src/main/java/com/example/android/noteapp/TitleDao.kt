package com.example.android.noteapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TitleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTitle(title : Title): Long

    @Delete
    suspend fun deleteTitle(title: Title)

    @Query("DELETE FROM subtitle WHERE idTitle = :idTitle")
    suspend fun deleteSubtitleWithTitle(idTitle : Int)

    @Query("SELECT * FROM title")
    fun getAllTitles(): LiveData<List<Title>>

    @Query("DELETE FROM title")
    suspend fun deleteAllTitles()
    @Query("DELETE FROM subtitle")
    suspend fun deleteAllSubtitles()


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSubtitle(subtitle: Subtitle ): Long

    @Transaction
    @Query("SELECT * FROM title")
    fun getTitlesWithSubtitles() : LiveData<List<TitleWithSubtitles>>

    @Delete
    suspend fun deleteSubtitle(subtitle: Subtitle)




    //    @Query("SELECT * FROM title WHERE id = :id")
//    @Transaction
//    @Query("SELECT * FROM title WHERE titleNote = :titleNote")
//    fun getTitleWithSubtitles(titleNote : String) : List<TitleWithSubtitles>


}