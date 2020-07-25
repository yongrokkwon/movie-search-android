package com.ksoft.ms.util

import android.net.ConnectivityManager
import android.os.Build
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandler @Inject constructor(
    private val connectivityManager: ConnectivityManager
) {
    val isConnected get() = connectivityManager.activeNetwork != null
}