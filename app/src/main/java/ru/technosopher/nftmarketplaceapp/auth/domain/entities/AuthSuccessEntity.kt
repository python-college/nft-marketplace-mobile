package ru.technosopher.nftmarketplaceapp.auth.domain.entities

data class AuthSuccessEntity(
    val address: String,
    val sessionId: String
)