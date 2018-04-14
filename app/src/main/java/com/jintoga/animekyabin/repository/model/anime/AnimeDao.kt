package com.jintoga.animekyabin.repository.model.anime

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Single


@Dao
interface AnimeDao {
    @Query("SELECT * FROM Anime")
    fun getAll(): Single<List<Anime>>

    @Query("SELECT * FROM Anime WHERE id IN (:ids)")
    fun loadAllByIds(ids: Long): List<Anime>

    @Insert
    fun insertAll(animes: List<Anime>)

    @Delete
    fun delete(anime: Anime)
}