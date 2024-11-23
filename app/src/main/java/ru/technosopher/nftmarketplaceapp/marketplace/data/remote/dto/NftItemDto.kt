package ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NftItemDto(
    @SerializedName("address")
    val address: String,
    @SerializedName("index")
    val index: String,
    @SerializedName("owner_address")
    val ownerAddress: String,
    @SerializedName("metadata")
    val metadata: NftMetadataDto,
    val collection: NftItemCollectionDto?,
    val previews: List<PreviewDto>
)


data class NftMetadataDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String?,
    @SerializedName("marketplace")
    val marketplace: String,
    @SerializedName("description")
    val description: String
)

data class NftItemCollectionDto(
    @SerializedName("address")
    val address: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String
)


