package com.jintoga.animekyabin.ui.animes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jintoga.animekyabin.R
import com.jintoga.animekyabin.databinding.ActivityAnimesBinding
import com.jintoga.animekyabin.helper.appComponent
import com.jintoga.animekyabin.helper.applyMarginTop
import com.jintoga.animekyabin.helper.getViewModel
import com.jintoga.animekyabin.repository.model.anime.Anime
import kotlinx.android.synthetic.main.activity_animes.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject


class AnimesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: ActivityAnimesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewDataBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_animes)
        setSupportActionBar(toolbar)
        toolbar.applyMarginTop()

        viewDataBinding.viewModel = getViewModel(viewModelFactory)
        val viewModel = viewDataBinding.viewModel!!
        val token = appComponent.authManager().getToken()
        if (token != null) {
            appComponent.tokenInterceptor().setToken(token)
            viewModel.loadAnimes(true, true)
        } else {
            viewModel.getClientCredentials()
        }

        viewModel.animes.observe(this, Observer<List<Anime>> {
            snackbar(rootView, it.toString())
        })
    }
}
