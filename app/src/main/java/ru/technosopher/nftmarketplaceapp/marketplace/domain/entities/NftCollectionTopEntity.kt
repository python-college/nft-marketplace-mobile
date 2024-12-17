package ru.technosopher.nftmarketplaceapp.marketplace.domain.entities

data class NftCollectionTopEntity(
    val collections: List<NftCollectionEntity>,
    val totalCount: String,
    val page: String,
    val pageSize: String
)
