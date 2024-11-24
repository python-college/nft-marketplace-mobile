package ru.technosopher.nftmarketplaceapp.marketplace.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NftEntity(
    val address: String,
    val index: String,
    val ownerAddress: String,
    val name: String,
    val image: String?,
    val description: String,
    val collectionName: String,
    val collectionAddress: String,
    val contractAddress: String?,
    val isForSale: Boolean,
    val priceValue: String?,
    val tokenName: String?
) : Parcelable
