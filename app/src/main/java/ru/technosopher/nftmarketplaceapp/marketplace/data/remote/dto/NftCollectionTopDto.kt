package ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NftCollectionTopDto(
    @SerializedName("collections")
    val collections: List<NftCollectionDto>,
    @SerializedName("total_count")
    val totalCount: String,
    @SerializedName("page")
    val page: String,
    @SerializedName("page_size")
    val pageSize: String
)
