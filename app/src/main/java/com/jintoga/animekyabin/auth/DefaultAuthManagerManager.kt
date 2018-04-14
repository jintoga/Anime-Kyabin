package com.jintoga.animekyabin.auth

import com.jintoga.animekyabin.repository.model.auth.ClientCredentials
import com.jintoga.animekyabin.repository.model.auth.ClientCredentialsRequest
import com.jintoga.animekyabin.repository.network.ClientApi
import io.reactivex.Observable

class DefaultAuthManagerManager(private val clientApi: ClientApi,
                                private val preferenceHelper: PreferenceHelper) : AuthManager {

    override fun grantClientCredentials(request: ClientCredentialsRequest)
            : Observable<ClientCredentials> =
            clientApi.grantClientCredentials(request)
                    .doOnNext { preferenceHelper.setAuthToken(it.accessToken) }

    override fun getToken(): String? = preferenceHelper.getAuthToken()

}