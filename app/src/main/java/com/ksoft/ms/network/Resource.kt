package com.ksoft.ms.network

data class Resource<T>(
    var status: Status,
    val data: T? = null,
    val error: Error? = null
) {
    companion object {
        fun <T> success(data: T?) = Resource(Status.SUCCESS, data, null)

        fun <T> error(error: Error, data: T? = null) = Resource(
            Status.ERROR,
            data,
            error
        )

        fun <T> loading(data: T? = null) = Resource(Status.LOADING, data, null)
    }
}

enum class Status { SUCCESS, ERROR, LOADING }