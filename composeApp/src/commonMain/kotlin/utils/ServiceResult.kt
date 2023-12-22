package utils

sealed interface ServiceResult {
    data class Loading(val isLoading: Boolean) : ServiceResult
    data class Success<T>(val data: T) : ServiceResult
    data class Error(val message: String) : ServiceResult
}