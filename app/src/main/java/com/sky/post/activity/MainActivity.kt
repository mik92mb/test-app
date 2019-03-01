package com.sky.post.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.post.BaseActivity
import com.sky.post.R
import com.sky.post.adapters.RecyclerViewAdapter
import com.sky.post.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel

    private val adapter = RecyclerViewAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.e("SONO IN onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        setRecycleViewModel()
        progressBar.show()
        viewModel.setCall()
        viewModel.success.observe(this, Observer {
            Timber.e("SONO IN SUCCESS")
            progressBar.hide()
            adapter.clearList()
            adapter.addAll(it)
            textView.text = getString(R.string.posts_found, it.size)
        })
        viewModel.error.observe(this, Observer {
            progressBar.hide()
            Timber.e("SONO IN ERROR")
        })
    }

    private fun setRecycleViewModel() {
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
    }

}
