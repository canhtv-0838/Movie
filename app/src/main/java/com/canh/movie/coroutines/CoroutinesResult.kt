package com.canh.movie.coroutines

sealed class CoroutinesResult<out T : Any> {
    class Success<out T : Any>(val data: T) : CoroutinesResult<T>()

    class Failed(val throwable: Throwable) : CoroutinesResult<Nothing>()
}

fun <T : Any, data> CoroutinesResult<T>.getData(
    onSuccess: (resultData: T) -> data,
    onFailed: (throwable: Throwable) -> data
): data = when (this) {
    is CoroutinesResult.Success -> onSuccess(this.data)
    is CoroutinesResult.Failed -> onFailed(this.throwable)
}
