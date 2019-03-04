package com.sky.post

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.sky.post.network.PostAPI
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val subscription by lazy { CompositeDisposable() }


    protected val postClient: PostAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PostAPI::class.java)


    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }


    protected fun Disposable.autoDispose() {
        subscription.add(this)
    }

}