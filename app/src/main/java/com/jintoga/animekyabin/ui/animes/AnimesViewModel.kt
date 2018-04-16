package com.jintoga.animekyabin.ui.animes

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.util.Log
import com.jintoga.animekyabin.auth.AuthManager
import com.jintoga.animekyabin.repository.Repository
import com.jintoga.animekyabin.repository.model.anime.Anime
import com.jintoga.animekyabin.repository.model.auth.ClientCredentialsRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AnimesViewModel @Inject constructor(private val repository: Repository,
                                          private val authManager: AuthManager)
    : ViewModel() {

    val isEmpty = ObservableBoolean(false)
    val isLoadError = ObservableBoolean(false)
    val isLoading = ObservableBoolean(false)
    val animes: MutableLiveData<List<Anime>> = MutableLiveData()

    fun loadAnimes(forceUpdate: Boolean, showLoadingUI: Boolean) {
        if (showLoadingUI) {
            isLoading.set(true)
        }
        if (forceUpdate) {
            repository.getAnimes()
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
                                isLoadError.set(true)
                                Log.e("E", it.localizedMessage)
                            },
                            { isLoading.set(false) }
                    )
        }
    }

    fun getClientCredentials() {
        val request = ClientCredentialsRequest(
                grantType = "client_credentials",
                clientId = "jintoga-vfgpk",
                clientSecret = "WdKuseLjVIQ9Q5Ubj1Ks96mViiisn"
        )
        authManager
                .grantClientCredentials(request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            Log.d("CC", it.accessToken)
                        },
                        { isLoadError.set(true) },
                        { isLoading.set(false) }
                )
    }
}