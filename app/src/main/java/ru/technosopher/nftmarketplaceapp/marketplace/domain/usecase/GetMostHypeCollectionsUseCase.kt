package ru.technosopher.nftmarketplaceapp.marketplace.domain.usecase

import ru.technosopher.nftmarketplaceapp.marketplace.domain.repository.MarketplaceRepository
import javax.inject.Inject

class GetMostHypeCollectionsUseCase @Inject constructor(
    private val repository: MarketplaceRepository
) {
    suspend operator fun invoke(page: String = "1",
                                pageSize: String = "20") = repository.getMostHypeCollections(page, pageSize)
}