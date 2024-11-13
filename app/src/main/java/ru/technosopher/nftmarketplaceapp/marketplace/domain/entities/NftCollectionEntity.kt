package ru.technosopher.nftmarketplaceapp.marketplace.domain.entities

data class NftCollectionEntity(
    val name: String,
    val address: String,
    val description: String,
    val imageUrl: String?
)
