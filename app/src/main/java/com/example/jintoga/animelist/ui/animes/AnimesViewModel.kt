package com.example.jintoga.animelist.ui.animes

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import android.util.Log
import com.example.jintoga.animelist.AnimeListApplication
import com.example.jintoga.animelist.repository.model.anime.Anime
import com.example.jintoga.animelist.repository.model.auth.ClientCredentialsRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AnimesViewModel : ViewModel() {

    val isLoadError = ObservableBoolean(false)
    val isLoading = ObservableBoolean(false)
    val items: ObservableList<Anime> = ObservableArrayList()

    fun loadAnimes(forceUpdate: Boolean, showLoadingUI: Boolean) {
        if (showLoadingUI) {
            isLoading.set(true)
        }
        if (forceUpdate) {
            AnimeListApplication
                    .appComponent
                    .repository()
                    .getAnimes()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                            {
                                items.clear()
                                items.addAll(it)
                            },
                            { isLoadError.set(true) },
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
        AnimeListApplication
                .appComponent
                .authManager()
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