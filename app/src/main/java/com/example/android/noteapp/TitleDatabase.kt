package com.example.android.noteapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
        entities = [Title::class, Subtitle::class],
        version = 3,
        exportSchema = false
)
abstract class TitleDatabase: RoomDatabase() {

    abstract fun getTitleDao(): TitleDao

    companion object{
        @Volatile
        private var INSTANCE : com.example.android.noteapp.TitleDatabase? = null

//        "PHILLIP LACKNER METHOD"

        private val LOCK = Any()

        operator fun invoke(context : Context) = INSTANCE ?:synchronized(LOCK) {
            INSTANCE ?: createDatabase(context).also { INSTANCE = it}
        }

        private fun createDatabase(context :Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TitleDatabase::class.java,
                "title_db.db"
            ).fallbackToDestructiveMigration().build()

//        ANUJ BHAIYA METHOD

//        fun getDatabase(context : Context): TitleDatabase{
//            return INSTANCE ?: synchronized(this){
//                val instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        TitleDatabase::class.java,
//                        "note_database.db"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }

    }
}