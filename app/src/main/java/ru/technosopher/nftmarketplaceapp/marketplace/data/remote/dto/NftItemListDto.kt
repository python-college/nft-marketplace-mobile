package ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NftItemListDto(
    @SerializedName("nft_items")
    val nftItemList: List<NftItemDto>
)
