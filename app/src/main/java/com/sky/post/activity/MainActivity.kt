package com.sky.post.activity

import android.app.ActivityOptions
import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.post.BaseActivity
import com.sky.post.R
import com.sky.post.adapters.RecyclerViewAdapter
import com.sky.post.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import com.sky.post.adapters.OnItemClick
import com.sky.post.network.model.Post
import timber.log.Timber

class MainActivity : BaseActivity(), OnItemClick {

    private lateinit var viewModel: MainViewModel

    private val adapter = RecyclerViewAdapter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        setRecycleViewModel()
        progressBar.show()
        viewModel.setCall()
        viewModel.success.observe(this, Observer {
            progressBar.hide()
            adapter.clearList()
            adapter.addAll(it)
            textView.text = getString(R.string.posts_found, it.size)
        })
        viewModel.error.observe(this, Observer {
            progressBar.hide()
        })
    }

    private fun setRecycleViewModel() {
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(post: Post, imageView: ImageView) {
        DetailsActivity.start(this, imageView, post.id, post.title)
    }

}
