package ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto

import com.google.gson.annotations.SerializedName
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftEntity

data class NftDto(
    @SerializedName("address")
    val address: String
)


