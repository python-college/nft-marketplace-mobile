package ru.technosopher.nftmarketplaceapp.core.domain.entities

data class NetworkError(
    val error: ApiError,
    val throwable: Throwable? = null
)
enum class ApiError {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN_ERROR,
    NETWORK_ERROR,
    UNKNOWN_RESPONSE
}