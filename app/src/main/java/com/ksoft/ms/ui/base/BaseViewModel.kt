package com.ksoft.ms.ui.base

import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel<P> : ViewModel() {

    private lateinit var presenter: WeakReference<P>

    fun getPresenter(): P? {
        return presenter.get()
    }

    fun setPresenter(presenter: P) {
        this.presenter = WeakReference(presenter)
    }

    fun clear() {
        presenter.clear()
    }

}