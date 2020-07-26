package com.ksoft.ms.ui.base

interface BasePresenter {

    fun showLoading()
    fun hideLoading()
    fun showToast(message: String?)

}