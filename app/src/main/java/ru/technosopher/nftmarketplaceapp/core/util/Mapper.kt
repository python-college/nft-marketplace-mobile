package ru.technosopher.nftmarketplaceapp.core.util


import retrofit2.HttpException
import ru.technosopher.nftmarketplaceapp.core.domain.entities.ApiError
import ru.technosopher.nftmarketplaceapp.core.domain.entities.NetworkError
import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto.NftItemDto
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftEntity
import java.io.IOException

fun NftItemDto.toEntity() = NftEntity(
    address
)

fun Throwable.toNetworkError(): NetworkError {
    val error = when(this) {
        is IOException -> ApiError.NETWORK_ERROR
        is HttpException -> ApiError.UNKNOWN_RESPONSE
        else -> ApiError.UNKNOWN_ERROR
    }
    return NetworkError(
        error = error,
        throwable = this
    )
}