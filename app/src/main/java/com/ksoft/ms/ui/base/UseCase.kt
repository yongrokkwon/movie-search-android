package com.ksoft.ms.ui.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class UseCase<Response, in Params> {

    abstract fun execute(params: Params): Flow<Response>

    operator fun invoke(params: Params): Flow<Response> = execute(params).flowOn(Dispatchers.IO)

}