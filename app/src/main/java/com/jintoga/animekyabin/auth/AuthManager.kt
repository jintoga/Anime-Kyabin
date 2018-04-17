package com.jintoga.animekyabin.auth

import com.jintoga.animekyabin.repository.model.auth.ClientCredentials
import com.jintoga.animekyabin.repository.model.auth.ClientCredentialsRequest
import io.reactivex.Observable
import java.util.*

interface AuthManager {
    val isTokenActive: Boolean
    fun grantClientCredentials(request: ClientCredentialsRequest): Observable<ClientCredentials>
    fun getExpireDate(): Date
    fun setTokenToInterceptor()
}