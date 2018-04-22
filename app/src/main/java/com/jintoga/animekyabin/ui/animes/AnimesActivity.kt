package com.jintoga.animekyabin.ui.animes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.TextView
import com.github.florent37.viewanimator.ViewAnimator
import com.jintoga.animekyabin.R
import com.jintoga.animekyabin.databinding.ActivityAnimesBinding
import com.jintoga.animekyabin.helper.appComponent
import com.jintoga.animekyabin.helper.applyMarginTop
import com.jintoga.animekyabin.helper.getNavigationBarHeight
import com.jintoga.animekyabin.helper.getViewModel
import com.jintoga.animekyabin.ui.animes.adapters.AnimesAdapter
import kotlinx.android.synthetic.main.activity_animes.*
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject


class AnimesActivity : AppCompatActivity() {

    companion object {
        private const val TITLE_ANIMATION_DURATION = 900L
    }

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

        if (!resources.getBoolean(R.bool.isLandscape)) {
            //Translucent bottom navigation for Portrait mode only
            rootView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            animesRecyclerView.setPadding(
                    animesRecyclerView.left,
                    animesRecyclerView.top,
                    animesRecyclerView.right,
                    getNavigationBarHeight())
        }

        viewModel = getViewModel(viewModelFactory)
        viewDataBinding.viewModel = viewModel

        init()

        if (savedInstanceState == null) {
            animateToolbar()
        }

        viewModel.snackbarMessage.observe(this, Observer {
            snackbar(rootView, it!!)
        })

        Handler().postDelayed({
            viewModel.loadAnimes(true, true)
        }, TITLE_ANIMATION_DURATION)
    }

    private fun init() {
        initRecyclerView()
        //Other inits
    }

    private fun initRecyclerView() {
        animesRecyclerView.setHasFixedSize(true)
        animesRecyclerView.layoutManager = GridLayoutManager(this, 3)
        adapter = AnimesAdapter(viewModel, animesRecyclerView)
        animesRecyclerView.adapter = adapter
    }

    private fun animateToolbar() {
        // this is gross but toolbar doesn't expose it's children to animate them :(
        val t = toolbar.getChildAt(0)
        if (t != null && t is TextView) {
            ViewAnimator
                    .animate(t)
                    .fadeIn()
                    .duration(TITLE_ANIMATION_DURATION)
                    .start()
        }
    }
}
