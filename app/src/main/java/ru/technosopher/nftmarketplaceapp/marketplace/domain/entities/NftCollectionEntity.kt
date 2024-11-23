package ru.technosopher.nftmarketplaceapp.marketplace.domain.entities

import kotlinx.parcelize.Parcelize
import android.os.Parcelable


@Parcelize
data class NftCollectionEntity (
    val address: String,
    val ownerAddress: String,
    val itemsCount: String,
    val name: String,
    val description: String,
    val image: String,
    val coverImage: String
) : Parcelable
