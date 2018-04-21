package com.jintoga.animekyabin.ui.animes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.TextView
import com.github.florent37.viewanimator.ViewAnimator
import com.jintoga.animekyabin.R
import com.jintoga.animekyabin.databinding.ActivityAnimesBinding
import com.jintoga.animekyabin.helper.appComponent
import com.jintoga.animekyabin.helper.applyMarginTop
import com.jintoga.animekyabin.helper.getViewModel
import com.jintoga.animekyabin.ui.animes.adapters.AnimesAdapter
import kotlinx.android.synthetic.main.activity_animes.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject


class AnimesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: ActivityAnimesBinding
    private lateinit var viewModel: AnimesViewModel
    private lateinit var adapter: AnimesAdapter

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

        viewModel = getViewModel(viewModelFactory)
        viewDataBinding.viewModel = viewModel

        init()

        viewModel.loadAnimes(true, true)

        viewModel.snackbarMessage.observe(this, Observer {
            snackbar(rootView, it!!)
        })

        viewModel.animes.observe(this, Observer {
            it?.let {
                adapter.setAnimes(it)
            }
        })
    }

    private fun init() {
        animesRecyclerView.setHasFixedSize(true)
        animesRecyclerView.layoutManager = GridLayoutManager(this, 3)
        adapter = AnimesAdapter(viewModel)
        animesRecyclerView.adapter = adapter
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
