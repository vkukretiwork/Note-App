package com.example.android.noteapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TitleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(title : NoteTitle): Long

    @Delete
    suspend fun delete(title: NoteTitle)

    @Query("SELECT * FROM title_notes_table order by id ASC")
    fun getAllTitles(): LiveData<List<NoteTitle>>

}