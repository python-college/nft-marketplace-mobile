package ru.technosopher.nftmarketplaceapp.marketplace.data.repository

import arrow.core.Either
import ru.technosopher.nftmarketplaceapp.core.domain.entities.NetworkError
import ru.technosopher.nftmarketplaceapp.core.util.toNetworkError
import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.MarketplaceApi
import ru.technosopher.nftmarketplaceapp.marketplace.data.util.toEntity
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftCollectionEntity
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftEntity
import ru.technosopher.nftmarketplaceapp.marketplace.domain.repository.MarketplaceRepository
import java.util.stream.Collectors
import javax.inject.Inject
import kotlin.streams.toList

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
            // TODO: implement API get list
            listOf(
                NftCollectionEntity(
                    address = "EQB3ff_6atPL1Kh7s-OnYgY3zWR85reh9osBl1CHLwLZulRx",
                    name = "Коллекция он чейн",
                    description = "нахцй",
                    image = "https://cache.tonapi.io/imgproxy/5OJVJYOMyGLeVOQRMWDYE29i-CmLt4crHGn3FUTkGcA/rs:fill:500:500:1/g:no/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL3B5dGhvbi1jb2xsZWdlL2JhbGlzaC9yZWZzL2hlYWRzL21haW4vc3RpY2tlci5wbmc.webp",
                    coverImage = "",
                    ownerAddress = "EQClMPGXnrsMa75Ko3T9CfzhKkzK0Hyt2LLjmg88qDcgVdew",
                    itemsCount = "26"
                ),
                NftCollectionEntity(
                    address = "EQB3ff_6atPL1Kh7s-OnYgY3zWR85reh9osBl1CHLwLZulRx",
                    name = "Коллекция он чейн",
                    description = "нахцй",
                    image = "https://cache.tonapi.io/imgproxy/5OJVJYOMyGLeVOQRMWDYE29i-CmLt4crHGn3FUTkGcA/rs:fill:500:500:1/g:no/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL3B5dGhvbi1jb2xsZWdlL2JhbGlzaC9yZWZzL2hlYWRzL21haW4vc3RpY2tlci5wbmc.webp",
                    coverImage = "",
                    ownerAddress = "EQClMPGXnrsMa75Ko3T9CfzhKkzK0Hyt2LLjmg88qDcgVdew",
                    itemsCount = "26"
                ),
                NftCollectionEntity(
                    address = "EQB3ff_6atPL1Kh7s-OnYgY3zWR85reh9osBl1CHLwLZulRx",
                    name = "Коллекция он чейн",
                    description = "нахцй",
                    image = "https://cache.tonapi.io/imgproxy/5OJVJYOMyGLeVOQRMWDYE29i-CmLt4crHGn3FUTkGcA/rs:fill:500:500:1/g:no/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL3B5dGhvbi1jb2xsZWdlL2JhbGlzaC9yZWZzL2hlYWRzL21haW4vc3RpY2tlci5wbmc.webp",
                    coverImage = "",
                    ownerAddress = "EQClMPGXnrsMa75Ko3T9CfzhKkzK0Hyt2LLjmg88qDcgVdew",
                    itemsCount = "26")
            )
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getCollection(address: String): Either<NetworkError, NftCollectionEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getCollectionItems(address: String): Either<NetworkError, List<NftEntity>> {
        return Either.catch {
//            api.getCollection(collectionAddress = address).toEntity()
            api.getCollectionItems(address).nftItemList.stream()
                .map {it.toEntity()}
                .collect(Collectors.toList())
        }.mapLeft { it.toNetworkError() }
    }

}