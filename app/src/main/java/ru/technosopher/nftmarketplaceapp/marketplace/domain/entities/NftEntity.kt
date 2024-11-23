package ru.technosopher.nftmarketplaceapp.marketplace.domain.entities

data class NftEntity(
    val address: String,
    val index: String,
    val ownerAddress: String,
    val name: String,
    val image: String?,
    val description: String,
    val collectionName: String,
    val collectionAddress: String
)
