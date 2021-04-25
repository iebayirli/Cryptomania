package com.iebayirli.cryptomania.base

import com.iebayirli.cryptomania.model.ResultCoin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseRepository {

    protected fun <T> safeFlowCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        block: suspend () -> Response<T>
    ): Flow<ResultCoin<T>> {
        return flow {
            val response = block.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) emit(ResultCoin.Success(response.body()!!))
            } else if (response.errorBody() != null) {
                val errorMessage = response.errorBody().toString()
                emit(ResultCoin.Error(errorMessage))
            }
        }.flowOn(dispatcher)
    }

}