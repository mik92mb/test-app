package com.sky.post.viewModels

import androidx.lifecycle.MutableLiveData
import com.sky.post.BASE_URL
import com.sky.post.BaseViewModel
import com.sky.post.network.PostAPI
import com.sky.post.network.model.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class MainViewModel : BaseViewModel() {

    val success = MutableLiveData<List<Post>>()
    val error = MutableLiveData<Throwable>()


    // FACCIAMO LA CHIAMATA
    fun getPost() {
        Timber.e("SONO IN CALL")
        postClient.getPost()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ lstPost ->
                success.value = lstPost
            }, { throwable ->
                error.value = throwable
                Timber.e(throwable, "Error!")
            })
            .autoDispose()
    }



}