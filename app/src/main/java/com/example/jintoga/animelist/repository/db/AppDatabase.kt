package com.example.jintoga.animelist.repository.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.jintoga.animelist.repository.model.anime.Anime
import com.example.jintoga.animelist.repository.model.anime.AnimeDao


@Database(entities = arrayOf(Anime::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao

    companion object {

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room
                        .databaseBuilder(context.applicationContext, AppDatabase::class.java, "animes-db")
                        .allowMainThreadQueries()
                        .build()
            }
            return instance!!
        }

        fun destroyInstance() {
            instance = null
        }
    }
}