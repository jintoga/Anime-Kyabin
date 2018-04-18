package com.jintoga.animekyabin.ui.animes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.github.florent37.viewanimator.ViewAnimator
import com.jintoga.animekyabin.R
import com.jintoga.animekyabin.databinding.ActivityAnimesBinding
import com.jintoga.animekyabin.helper.appComponent
import com.jintoga.animekyabin.helper.applyMarginTop
import com.jintoga.animekyabin.helper.getViewModel
import com.jintoga.animekyabin.repository.model.anime.Anime
import kotlinx.android.synthetic.main.activity_animes.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast
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

        if (savedInstanceState == null) {
            animateToolbar()
        }

        viewDataBinding.viewModel = getViewModel(viewModelFactory)

        val viewModel = viewDataBinding.viewModel ?: return

        viewModel.loadAnimes(true, true)

        viewModel.animes.observe(this, Observer<List<Anime>> {
            toast("Loaded")
        })

        viewModel.snackbarMessage.observe(this, Observer {
            snackbar(rootView, it!!)
        })
    }


    private fun animateToolbar() {
        // this is gross but toolbar doesn't expose it's children to animate them :(
        val t = toolbar.getChildAt(0)
        if (t != null && t is TextView) {
            ViewAnimator
                    .animate(t)
                    .fadeIn()
                    .duration(900)
                    .start()
        }
    }
}
