package com.jintoga.animekyabin.ui.animes

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.jintoga.animekyabin.BuildConfig
import com.jintoga.animekyabin.auth.AuthManager
import com.jintoga.animekyabin.repository.Repository
import com.jintoga.animekyabin.repository.model.anime.Anime
import com.jintoga.animekyabin.repository.model.auth.ClientCredentials
import com.jintoga.animekyabin.repository.model.auth.ClientCredentialsRequest
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AnimesViewModel @Inject constructor(private val repository: Repository,
                                          private val authManager: AuthManager)
    : ViewModel() {

    val isEmpty = ObservableBoolean(false)
    val isLoadError = ObservableBoolean(false)
    val snackbarMessage = MutableLiveData<String>()
    val isLoading = ObservableBoolean(false)
    val animes = MutableLiveData<List<Anime>>()

    fun loadAnimes(forceUpdate: Boolean, showLoadingUI: Boolean) {
        if (showLoadingUI) {
            isLoading.set(true)
        }
        if (forceUpdate) {
            getLoadAnimesObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                            {
                                animes.value = it
                                with(animes) {
                                    isEmpty.set(it.isEmpty())
                                }
                            },
                            {
                                isLoading.set(false)
                                isLoadError.set(true)
                                snackbarMessage.value = it.localizedMessage
                            },
                            {
                                isLoading.set(false)
                            }
                    )
        }
    }

    private fun getLoadAnimesObservable(): Observable<List<Anime>> {
        if (authManager.isTokenActive) return loadAnimesObservable()
        else return grantClientCredentials().flatMap { return@flatMap loadAnimesObservable() }
    }

    private fun loadAnimesObservable(): Observable<List<Anime>> {
        authManager.setTokenToInterceptor()
        return repository.getAnimes()
    }

    private fun grantClientCredentials(): Observable<ClientCredentials> =
            authManager.grantClientCredentials(ClientCredentialsRequest(
                    grantType = BuildConfig.GRANT_TYPE,
                    clientId = BuildConfig.CLIENT_ID,
                    clientSecret = BuildConfig.CLIENT_SERCRET
            ))
}