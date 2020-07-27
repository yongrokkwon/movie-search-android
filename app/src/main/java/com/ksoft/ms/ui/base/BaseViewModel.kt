package com.ksoft.ms.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ksoft.ms.Event
import java.lang.ref.WeakReference

abstract class BaseViewModel<P> : ViewModel() {

    val navDirections = MutableLiveData<Event<Any>>()

    private lateinit var presenter: WeakReference<P>

    fun getPresenter(): P? {
        return presenter.get()
    }

    fun setPresenter(presenter: P) {
        this.presenter = WeakReference(presenter)
    }

    fun clear() {
        if (::presenter.isInitialized) presenter.clear()
    }

}