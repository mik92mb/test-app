package com.sky.post.viewModels

import androidx.lifecycle.MutableLiveData
import com.sky.post.BaseViewModel
import com.sky.post.network.model.Comment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel : BaseViewModel() {

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