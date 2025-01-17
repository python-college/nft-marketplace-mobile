package ru.technosopher.nftmarketplaceapp.marketplace.data.util

import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto.NftCollectionDto
import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto.NftCollectionTopDto
import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto.NftItemDto
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftCollectionEntity
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftCollectionTopEntity
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftEntity
import java.util.stream.Collectors
import kotlin.streams.toList

//fun NftCollectionDto.toEntity() = NftItemCollectionEntity(
//    name = metadata.name,
//    address = address,
//    description = metadata.description,
//    imageUrl = metadata.image
//)

fun NftCollectionDto.toEntity() = NftCollectionEntity(
    address = address,
    ownerAddress = ownerAddress,
    itemsCount = itemsCount,
    name = metadata.name,
    description = metadata.description,
    image = metadata.image ?: "",
    coverImage = metadata.coverImage ?: ""
)

fun NftItemDto.toEntity() = NftEntity(
    address = address,
    index = index,
    ownerAddress = ownerAddress,
    name = metadata.name,
    description = metadata.description,
    image = metadata.image,
    collectionAddress = collection?.address ?: "",
    collectionName = collection?.name ?: "",
    isForSale = sale != null,
    priceValue = sale?.price?.value,
    tokenName = sale?.price?.tokenName,
    contractAddress = sale?.contractAddress
)

fun NftCollectionTopDto.toEntity() = NftCollectionTopEntity(
    collections = collections.stream()
        .map { it.toEntity() }
        .collect(Collectors.toList()),
    totalCount = totalCount,
    page = page,
    pageSize = pageSize
)