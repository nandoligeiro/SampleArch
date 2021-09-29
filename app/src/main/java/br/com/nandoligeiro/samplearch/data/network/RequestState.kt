package br.com.nandoligeiro.samplearch.data.network

sealed class RequestState<out T> {
    object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(
        val t: Throwable,
        var consumed: Boolean = false
    ) : RequestState<Nothing>()
}