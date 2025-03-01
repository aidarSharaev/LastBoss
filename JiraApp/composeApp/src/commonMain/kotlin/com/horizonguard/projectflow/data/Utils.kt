package com.horizonguard.projectflow.data

import com.horizonguard.projectflow.utils.AppException

internal fun <T> Result<T>.getValue(): T? {
    return this.getOrNull()?.let { value: T ->
        value
    }
}

// todo()
internal fun <T> Result<T>.ifSuccess(lambda: (T) -> Unit) {
    if (this.isSuccess) {
        lambda(this.getValue()!!)
    }
}

internal fun <T> Result<T>.getExc(): Throwable {
    return this.exceptionOrNull() ?: AppException.CommonException()
}

internal fun <T, R> Result<T>.resultFailure(): Result<R> {
    return Result.failure(this.getExc())
}

internal class ApiCall<T>(
    val data: T,
)

internal fun <T> failure(
    exc: Throwable = AppException.CommonException(),
): Result<T> {
    return Result.failure(exc)
}
