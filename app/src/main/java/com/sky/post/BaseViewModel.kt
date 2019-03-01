package com.sky.post

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val subscription by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }


    protected fun Disposable.autoDispose() {
        subscription.add(this)
    }

}