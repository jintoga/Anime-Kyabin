package com.jintoga.animekyabin.auth

import com.jintoga.animekyabin.repository.model.auth.ClientCredentials
import com.jintoga.animekyabin.repository.model.auth.ClientCredentialsRequest
import io.reactivex.Observable

interface AuthManager {
    fun getToken(): String?
    fun grantClientCredentials(request: ClientCredentialsRequest): Observable<ClientCredentials>
}