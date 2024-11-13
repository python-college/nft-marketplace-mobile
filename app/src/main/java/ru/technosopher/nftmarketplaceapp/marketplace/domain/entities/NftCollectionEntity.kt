package ru.technosopher.nftmarketplaceapp.marketplace.domain.entities

import java.io.Serializable

data class NftCollectionEntity (
    val address: String,
    val name: String,
    val description: String,
    val imageUrl: String?
)
