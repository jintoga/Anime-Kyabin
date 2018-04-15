package com.jintoga.animekyabin.ui.animes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jintoga.animekyabin.AKApp
import com.jintoga.animekyabin.R
import com.jintoga.animekyabin.helper.applyToolbarMarginTop
import com.jintoga.animekyabin.repository.model.anime.Anime
import kotlinx.android.synthetic.main.activity_animes.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.design.snackbar


class AnimesActivity : AppCompatActivity() {

    private lateinit var viewModel: AnimesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animes)

        setSupportActionBar(toolbar)
        applyToolbarMarginTop(toolbar)

        viewModel = obtainViewModel(this)
        if (AKApp.appComponent.authManager().getToken() != null) {
            viewModel.loadAnimes(true, true)
        } else {
            viewModel.getClientCredentials()
        }

        viewModel.animes.observe(this, Observer<List<Anime>> {
            snackbar(rootView, it.toString())
        })
    }

    private fun obtainViewModel(animesActivity: AnimesActivity): AnimesViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(animesActivity.application)
        return ViewModelProviders.of(this, factory).get(AnimesViewModel::class.java)
    }
}
