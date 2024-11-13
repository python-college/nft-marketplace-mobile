package ru.technosopher.nftmarketplaceapp.marketplace.data.util

import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto.NftCollectionDto
import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto.NftItemDto
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftCollectionEntity
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftEntity

fun NftItemDto.toEntity() = NftEntity(
    address
)

fun NftCollectionDto.toEntity() = NftCollectionEntity(
    name = metadata.name,
    address = address,
    description = metadata.description,
    imageUrl = metadata.image
)