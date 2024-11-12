package ru.technosopher.nftmarketplaceapp.marketplace.data.repository

import arrow.core.Either
import ru.technosopher.nftmarketplaceapp.core.domain.entities.NetworkError
import ru.technosopher.nftmarketplaceapp.core.domain.entities.Status
import ru.technosopher.nftmarketplaceapp.core.util.toNetworkError
import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.MarketplaceApi
import ru.technosopher.nftmarketplaceapp.marketplace.data.util.toEntity
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftCollectionEntity
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftEntity
import ru.technosopher.nftmarketplaceapp.marketplace.domain.repository.MarketplaceRepository
import javax.inject.Inject

class MarketplaceRepositoryImpl @Inject constructor(
    private val api: MarketplaceApi
) : MarketplaceRepository {
    override suspend fun getNft(nftAddress: String): Either<NetworkError, NftEntity> {
        return Either.catch {
            api.getNft(nftAddress).toEntity()
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getCollections(): Either<NetworkError, List<NftCollectionEntity>> {
        return Either.catch {
            // Temporally returns hard-coded collection list
            listOf(
                NftCollectionEntity(
                    address = "EQB3ff_6atPL1Kh7s-OnYgY3zWR85reh9osBl1CHLwLZulRx",
                    name = "Коллекция он чейн",
                    description = "нахцй"
                ),
                NftCollectionEntity(
                    address = "EQB3ff_6atPL1Kh7s-OnYgY3zWR85reh9osBl1CHLwLZulRx",
                    name = "Коллекция он чейн",
                    description = "нахцй"
                ),
                NftCollectionEntity(
                    address = "EQB3ff_6atPL1Kh7s-OnYgY3zWR85reh9osBl1CHLwLZulRx",
                    name = "Коллекция он чейн",
                    description = "нахцй"
                )
            )
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getCollection(address: String): Either<NetworkError, NftCollectionEntity> {
        return Either.catch {
            api.getCollection(collectionAddress = address).toEntity()
        }.mapLeft { it.toNetworkError() }
    }

}