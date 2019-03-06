package com.sky.post.activity

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sky.post.BaseActivity
import com.sky.post.R
import com.sky.post.adapters.RecyclerViewAdapter
import com.sky.post.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import com.sky.post.adapters.OnItemClick
import com.sky.post.data.local.PostEntity

class MainActivity : BaseActivity(), OnItemClick, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var viewModel: MainViewModel

    private val adapter = RecyclerViewAdapter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        setRecycleViewModel()
        setSwipeRefreshLayout()
        callGetPost()
    }

    private fun setRecycleViewModel() {
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private fun setSwipeRefreshLayout() {
        swipeRefresh.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN)
        swipeRefresh.setOnRefreshListener(this)
    }


    private fun callGetPost() {
        progressBar.show()
        viewModel.getPost()
        viewModel.success.observe(this, Observer {
            progressBar.hide()
            swipeRefresh.isRefreshing = false
            adapter.clearList()
            adapter.addAll(it)
            textView.text = getString(R.string.posts_found, it.size)
        })
        viewModel.error.observe(this, Observer {
            progressBar.hide()
            swipeRefresh.isRefreshing = false
        })
    }


    override fun onRefresh() {
        viewModel.getPost()
    }

    override fun onItemClick(post: PostEntity, imageView: ImageView) {
        DetailsActivity.start(this, imageView, post.id, post.title)
    }


    override fun onSupportNavigateUp() = true

}
