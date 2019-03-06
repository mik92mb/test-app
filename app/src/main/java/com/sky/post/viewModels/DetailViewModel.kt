package com.sky.post.viewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sky.post.BaseViewModel
import com.sky.post.data.local.CommentEntity
import com.sky.post.data.network.model.Comment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val success = MutableLiveData<List<CommentEntity>>()
    val error = MutableLiveData<Throwable>()


    fun getCommentsbyId(id: Int) {
        postClient.getCommentsbyId(id)
            .subscribeOn(Schedulers.io())
            .flatMapIterable { it }
            .filter {
                it.postId == id
            }
            .map {
                val comment = it.toCommentsEntity()
                db.entityDao.insertComment(comment)
                comment
            }
            .toList()
            .onErrorReturn {
                db.entityDao.getComments(id)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                success.value = it
            }, {
                error.value = it
            }).autoDispose()
    }

}