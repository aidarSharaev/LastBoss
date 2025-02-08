package com.horizonguard.projectflow.data

import com.horizonguard.projectflow.utils.AppException

internal fun <T> Result<T>.getValue(): T? {
    return this.getOrNull()?.let { value: T ->
        value
    }
}

internal fun <T> Result<T>.getExc(): Throwable {
    return this.exceptionOrNull() ?: AppException.CommonException()
}

internal fun <T, R> Result<T>.resultFailure(): Result<R> {
    return Result.failure(this.getExc())
}

internal object ApiCall

internal val successApiCallResult = Result.success(ApiCall)
