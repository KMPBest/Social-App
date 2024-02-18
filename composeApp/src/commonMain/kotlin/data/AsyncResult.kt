package data

sealed interface AsyncResult<out T> {
    data class Success<T>(val data: T) : AsyncResult<T>
    data class Error(val exception: Throwable? = null) : AsyncResult<Nothing>
    data object Loading : AsyncResult<Nothing>
}
