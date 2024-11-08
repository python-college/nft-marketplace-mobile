package ru.technosopher.nftmarketplaceapp.marketplace.data.repository

import arrow.core.Either
import ru.technosopher.nftmarketplaceapp.core.domain.entities.NetworkError
import ru.technosopher.nftmarketplaceapp.core.domain.entities.Status
import ru.technosopher.nftmarketplaceapp.core.util.toEntity
import ru.technosopher.nftmarketplaceapp.core.util.toNetworkError
import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.MarketplaceApi
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

}