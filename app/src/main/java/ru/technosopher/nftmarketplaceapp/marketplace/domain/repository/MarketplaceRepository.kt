package ru.technosopher.nftmarketplaceapp.marketplace.domain.repository

import arrow.core.Either
import ru.technosopher.nftmarketplaceapp.core.domain.entities.NetworkError
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftCollectionEntity
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftEntity

interface MarketplaceRepository {
    suspend fun getNft(nftAddress : String): Either<NetworkError, NftEntity>
    suspend fun getCollections(): Either<NetworkError, List<NftCollectionEntity>>
    suspend fun getCollection(address: String): Either<NetworkError, NftCollectionEntity>
}