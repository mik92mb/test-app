package com.sky.post

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.sky.post.data.local.AppDatabase
import com.sky.post.data.network.PostAPI
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val subscription by lazy { CompositeDisposable() }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .cache(Cache(application.cacheDir, 1024*1024))
        .connectTimeout(5, TimeUnit.SECONDS)
        //.retryOnConnectionFailure()
        .build()

    protected val postClient: PostAPI = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PostAPI::class.java)


    protected val db = Room.databaseBuilder(
        application.applicationContext,
        AppDatabase::class.java,
        "dataBasePost"
    ).build()


    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }


    protected fun Disposable.autoDispose() {
        subscription.add(this)
    }

}