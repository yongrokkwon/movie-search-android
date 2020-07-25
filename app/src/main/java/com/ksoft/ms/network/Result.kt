package com.ksoft.ms.network

sealed class Result<out F, out R> {
    data class Fail<out F>(val failure: F) : Result<F, Nothing>()
    data class Success<out R>(val result: R) : Result<Nothing, R>()

    fun fold(fnF: (F) -> Unit, fnR: (R) -> Unit): Any = when (this) {
        is Fail -> fnF(failure)
        is Success -> fnR(result)
    }
}
