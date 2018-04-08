package com.example.jintoga.animelist.repository

import com.example.jintoga.animelist.repository.model.anime.Anime
import io.reactivex.Observable

interface Repository {
    fun getAnimes(): Observable<List<Anime>>
    fun getAnimesFromDb(): Observable<List<Anime>>
    fun getAnimesFromApi(): Observable<List<Anime>>
    fun saveAnimesToDb(animes: List<Anime>)
}