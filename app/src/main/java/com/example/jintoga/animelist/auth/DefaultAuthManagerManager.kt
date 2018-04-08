package com.example.jintoga.animelist.auth

import com.example.jintoga.animelist.repository.model.auth.ClientCredentials
import com.example.jintoga.animelist.repository.model.auth.ClientCredentialsRequest
import com.example.jintoga.animelist.repository.network.ClientApi
import io.reactivex.Observable

class DefaultAuthManagerManager(private val clientApi: ClientApi,
                                private val preferenceHelper: PreferenceHelper) : AuthManager {

    override fun grantClientCredentials(request: ClientCredentialsRequest)
            : Observable<ClientCredentials> =
            clientApi.grantClientCredentials(request)
                    .doOnNext { preferenceHelper.setAuthToken(it.accessToken) }

    override fun getToken(): String? = preferenceHelper.getAuthToken()

}