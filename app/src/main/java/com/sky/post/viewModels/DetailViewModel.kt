package com.sky.post.viewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sky.post.BaseViewModel
import com.sky.post.network.model.Comment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val success = MutableLiveData<List<Comment>>()
    val error = MutableLiveData<Throwable>()


    fun getCommentsbyId(id: Int) {
        postClient.getCommentsbyId(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapIterable { it }
            .filter {
                it.id < 100
            }
            .toList()
            .subscribe({
                success.value = it
            }, {
                error.value = it
            }).autoDispose()
    }

}