package com.ksoft.ms.ui.base

import com.ksoft.ms.exception.Failure
import com.ksoft.ms.network.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class UseCase<out Type, in Params> {

    abstract suspend fun run(params: Params): Result<Failure, Type>

    operator fun invoke(params: Params, onResult: (Result<Failure, Type>) -> Unit = {}) {
        val job = GlobalScope.async(Dispatchers.IO) { run(params) }
        GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }
    }

    class None
}