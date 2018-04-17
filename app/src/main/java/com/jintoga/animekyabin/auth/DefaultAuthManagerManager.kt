package com.jintoga.animekyabin.auth

import com.jintoga.animekyabin.repository.model.auth.ClientCredentials
import com.jintoga.animekyabin.repository.model.auth.ClientCredentialsRequest
import com.jintoga.animekyabin.repository.network.ClientApi
import com.jintoga.animekyabin.repository.network.XTokenInterceptor
import io.reactivex.Observable
import java.util.*

class DefaultAuthManagerManager(private val clientApi: ClientApi,
                                private val preferenceHelper: PreferenceHelper,
                                private val xTokenInterceptor: XTokenInterceptor) : AuthManager {

    override fun getExpireDate(): Date = Date(preferenceHelper.getExpireTime() * 1000L)

    override val isTokenActive: Boolean
        get() = Date().before(getExpireDate())

    override fun grantClientCredentials(request: ClientCredentialsRequest)
            : Observable<ClientCredentials> =
            clientApi.grantClientCredentials(request)
                    .doOnNext(preferenceHelper::setClientCredentials)

    override fun setTokenToInterceptor() {
        xTokenInterceptor.setToken(preferenceHelper.getAuthToken()!!)
    }
}