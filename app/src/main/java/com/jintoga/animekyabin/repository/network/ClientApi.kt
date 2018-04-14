package com.jintoga.animekyabin.repository.network

import com.jintoga.animekyabin.repository.model.anime.Anime
import com.jintoga.animekyabin.repository.model.auth.ClientCredentials
import com.jintoga.animekyabin.repository.model.auth.ClientCredentialsRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ClientApi {

    @POST("auth/access_token")
    fun grantClientCredentials(@Body request: ClientCredentialsRequest)
            : Observable<ClientCredentials>

    @GET("browse/anime")
    fun getAnimes(@Query("page") page: Int?): Observable<List<Anime>>
}