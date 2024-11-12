package ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NftCollectionDto(
    @SerializedName("address")
    val address: String,
    @SerializedName("owner_address")
    val ownerAddress: String,
    @SerializedName("items_count")
    val itemsCount: String,
    @SerializedName("metadata")
    val metadata: CollectionMetadataDto,
    @SerializedName("previews")
    val previews: List<PreviewDto>

)

data class CollectionMetadataDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String?,
    @SerializedName("cover_image")
    val coverImage: String?,
    @SerializedName("marketplace")
    val marketplace: String,
    @SerializedName("external_url")
    val externalUrl: String?,
    @SerializedName("social_links")
    val socialLinks: List<String>
)
