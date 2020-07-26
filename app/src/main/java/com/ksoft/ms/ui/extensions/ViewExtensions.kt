package com.ksoft.ms.ui.extensions

import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide(type: Int = View.GONE) {
    this.visibility = type
}