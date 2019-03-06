package com.sky.post.viewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sky.post.BaseViewModel
import com.sky.post.data.local.PostEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainViewModel(application: Application) : BaseViewModel(application) {

    val success = MutableLiveData<List<PostEntity>>()
    val error = MutableLiveData<Throwable>()


    // FACCIAMO LA CHIAMATA
    fun getPost() {
        Timber.e("SONO IN CALL")
        postClient.getPost()
            .subscribeOn(Schedulers.io())
            .flatMapIterable { it }
            .map {
                val posts = it.toPostEntity()
                db.entityDao.insertPost(posts)
                posts
            }
            .toList()
            .onErrorReturn {
                db.entityDao.getAllPosts()
            }
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