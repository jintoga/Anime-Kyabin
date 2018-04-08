package com.example.jintoga.animelist.auth

import com.example.jintoga.animelist.repository.model.auth.ClientCredentials
import com.example.jintoga.animelist.repository.model.auth.ClientCredentialsRequest
import io.reactivex.Observable

interface AuthManager {
    fun getToken(): String?
    fun grantClientCredentials(request: ClientCredentialsRequest): Observable<ClientCredentials>
}