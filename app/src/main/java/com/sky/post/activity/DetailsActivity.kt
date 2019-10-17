package com.sky.post.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.post.BaseActivity
import com.sky.post.R
import com.sky.post.adapters.CommentAdapter
import com.sky.post.viewModels.DetailViewModel
import kotlinx.android.synthetic.main.activity_details.*
import timber.log.Timber
import com.google.android.material.appbar.AppBarLayout


class DetailsActivity : BaseActivity(), AppBarLayout.OnOffsetChangedListener {


    private lateinit var viewModel: DetailViewModel
    private val commentAdapter = CommentAdapter(this)

    // METODO STATICO DI CLASSE - Coincide con static in Java
    companion object {
        const val BUNDLE_ID = "ID"
        const val BUNDLE_TITLE = "TITLE"

        fun start(activity: AppCompatActivity, imageView: ImageView, id: Int, title: String) {
            val intent = Intent(activity, DetailsActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                activity,
                imageView,
                "transition"
            )
            intent.putExtra(BUNDLE_ID, id)
            intent.putExtra(BUNDLE_TITLE, title)
            activity.startActivity(intent, options.toBundle())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val id = intent.getIntExtra(BUNDLE_ID, 0)
        val title = intent.getStringExtra(BUNDLE_TITLE)

        setRecyclerView()
        setToolbar(title)

        appBar.addOnOffsetChangedListener(this)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.getCommentsbyId(id)
        viewModel.success.observe(this, Observer {
            commentAdapter.clearAll()
            commentAdapter.addComments(it)
        })
        viewModel.error.observe(this, Observer {
            Timber.e(it.toString())
        })
    }

    private fun setRecyclerView() {
        with(recyclerCommenti) {
            layoutManager = LinearLayoutManager(this@DetailsActivity, RecyclerView.VERTICAL, false)
            adapter = commentAdapter
        }
    }

    private fun setToolbar(title: String) {
        setSupportActionBar(toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.title = title
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val isCollasped = appBarLayout.totalScrollRange + verticalOffset == 0
        supportActionBar?.setDisplayShowTitleEnabled(isCollasped)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}

