package ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PreviewDto(
    @SerializedName("resolution")
    val resolution: String,
    @SerializedName("url")
    val url: String
)
