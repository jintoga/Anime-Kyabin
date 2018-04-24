package com.jintoga.animekyabin.ui.animes

import `in`.srain.cube.views.ptr.PtrDefaultHandler.checkContentCanBePulledDown
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
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
import com.jintoga.animekyabin.ui.widget.PullToRefreshHeader
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

    private var canBePullDown = false

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

        viewModel.snackbarMessage.observe(this, Observer { message ->
            message?.let {
                snackbar(rootView, it)
            }
        })

        Handler().postDelayed({
            swipeToRefreshLayout.visibility = View.VISIBLE
            viewModel.loadAnimes(true, true)
        }, TITLE_ANIMATION_DURATION)
    }

    private fun init() {
        initRecyclerView()
        initPullToRefreshLayout()
        //Other inits
    }

    private fun initRecyclerView() {
        animesRecyclerView.setHasFixedSize(true)
        animesRecyclerView.layoutManager = GridLayoutManager(this, 3)
        adapter = AnimesAdapter(viewModel, animesRecyclerView)
        animesRecyclerView.adapter = adapter

        setToolbarCollapseExpandBehavior(false)
        animesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                //Allow collapse/expand when RecyclerView is scrollable vertically and is scrolling up
                val canCollapseExpand = animesRecyclerView.canScrollVertically(-1)
                setToolbarCollapseExpandBehavior(canCollapseExpand)
            }
        })
    }

    private fun initPullToRefreshLayout() {
        val ptrHeader = PullToRefreshHeader(this)
        swipeToRefreshLayout.headerView = ptrHeader

        appBarLayout.addOnOffsetChangedListener { _, verticalOffset ->
            //Allow pull to refresh only when toolbar is fully expanded
            canBePullDown = verticalOffset >= 0
        }

        swipeToRefreshLayout.resistance = 3.0F
        swipeToRefreshLayout.setRatioOfHeaderHeightToRefresh(1.0F)
        swipeToRefreshLayout.setPtrHandler(object : PtrHandler {
            override fun checkCanDoRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean =
                    canBePullDown && checkContentCanBePulledDown(frame, animesRecyclerView, header)

            override fun onRefreshBegin(frame: PtrFrameLayout) {
                frame.postDelayed({ frame.refreshComplete() }, 900)
            }
        })

        swipeToRefreshLayout.visibility = View.GONE
    }

    private fun setToolbarCollapseExpandBehavior(canCollapseExpand: Boolean) {
        val toolbarLayoutParams = toolbar.layoutParams as AppBarLayout.LayoutParams
        if (canCollapseExpand) {
            toolbarLayoutParams.scrollFlags =
                    AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
                    AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
        } else {
            toolbarLayoutParams.scrollFlags = 0
        }
        toolbar.layoutParams = toolbarLayoutParams
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
