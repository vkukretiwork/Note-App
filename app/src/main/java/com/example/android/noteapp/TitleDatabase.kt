package com.example.android.noteapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(
//    entities = [Title::class],
//    version = 1
//)
//abstract class TitleDatabase: RoomDatabase() {
//    abstract fun getTitleDao(): TitleDao
//
//    companion object{
//        @Volatile
//        private var instance : TitleDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context : Context) = instance ?:synchronized(LOCK) {
//            instance ?: createDatabase(context).also { instance = it}
//        }
//
//        private fun createDatabase(context :Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                TitleDatabase::class.java,
//                "title_db.db"
//            ).build()
//
//    }
//}