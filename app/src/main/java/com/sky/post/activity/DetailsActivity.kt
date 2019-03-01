package com.sky.post.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.sky.post.BaseActivity
import com.sky.post.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : BaseActivity() {

    // METODO STATICO DI CLASSE - Coincide con static in Java
    companion object {
        fun start(activity: AppCompatActivity, imageView: ImageView, id: Int, title: String) {
            val intent = Intent(activity, DetailsActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(activity, imageView, "transition")
            intent.putExtra("id", id)
            intent.putExtra("title", title)
            activity.startActivity(intent, options.toBundle())

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val id = intent.getSerializableExtra("id")
        val title = intent.getSerializableExtra("title")
        collapsingToolbar.title = title.toString()
    }

}

