package com.rangeseekbar.app.data.datasource

import com.rangeseekbar.app.data.pojo.DataWrapper
import com.rangeseekbar.app.data.pojo.ResponseBody
import com.rangeseekbar.app.exception.ServerException

open class BaseDataSource {

    suspend fun <T> execute(callAPI: suspend () -> ResponseBody<T>): DataWrapper<T> {
        return try {
            val result = callAPI()

            when (result.responseCode) {
                0, 2, 3, 4, 11 -> {
                    DataWrapper(result, ServerException(result.message, result.responseCode))
                }
                else -> {
                    DataWrapper(result, null)
                }
            }
        } catch (e: Throwable) {
            DataWrapper(null, e)
        }
    }

}