package com.jintoga.animekyabin.repository

import com.jintoga.animekyabin.repository.db.AppDatabase
import com.jintoga.animekyabin.repository.model.anime.Anime
import com.jintoga.animekyabin.repository.network.ClientApi
import io.reactivex.Observable


class RepositoryManager(private val clientApi: ClientApi,
                        private val database: AppDatabase) : Repository {

    override fun getAnimes(): Observable<List<Anime>> =
            Observable.concatArray(
                    getAnimesFromDb(),
                    getAnimesFromApi()
            )

    override fun getAnimesFromDb(): Observable<List<Anime>> =
            database.animeDao().getAll().toObservable()

    override fun getAnimesFromApi(): Observable<List<Anime>> =
            clientApi.getAnimes(3)
                    .toList()
                    .toObservable()
                    .doOnNext { saveAnimesToDb(it) }

    override fun saveAnimesToDb(animes: List<Anime>) {
        database.animeDao().insertAll(animes)
    }

}