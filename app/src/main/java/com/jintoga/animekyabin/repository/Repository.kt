package com.jintoga.animekyabin.repository

import com.jintoga.animekyabin.repository.model.anime.Anime
import io.reactivex.Observable

interface Repository {
    fun getAnimes(): Observable<List<Anime>>
    fun getAnimesFromDb(): Observable<List<Anime>>
    fun getAnimesFromApi(page: Int? = null): Observable<List<Anime>>
    fun saveAnimesToDb(animes: List<Anime>)
}