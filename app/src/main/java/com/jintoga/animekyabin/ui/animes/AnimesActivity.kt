package com.jintoga.animekyabin.ui.animes

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jintoga.animekyabin.AnimeListApplication
import com.jintoga.animekyabin.R


class AnimesActivity : AppCompatActivity() {

    private lateinit var viewModel: AnimesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animes)

        viewModel = obtainViewModel(this)
        if (AnimeListApplication.appComponent.authManager().getToken() != null) {
            viewModel.loadAnimes(true, true)
        } else {
            viewModel.getClientCredentials()
        }
    }

    private fun obtainViewModel(animesActivity: AnimesActivity): AnimesViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(animesActivity.application)
        return ViewModelProviders.of(this, factory).get(AnimesViewModel::class.java)
    }
}
