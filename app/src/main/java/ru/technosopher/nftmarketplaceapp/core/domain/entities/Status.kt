package ru.technosopher.nftmarketplaceapp.core.domain.entities

data class Status<T>(
    val statusCode: Int,
    val value: T,
    val errors: Throwable?
)