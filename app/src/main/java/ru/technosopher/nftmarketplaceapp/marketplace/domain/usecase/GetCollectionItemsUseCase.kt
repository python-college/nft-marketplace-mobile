package ru.technosopher.nftmarketplaceapp.marketplace.domain.usecase

import ru.technosopher.nftmarketplaceapp.marketplace.domain.repository.MarketplaceRepository
import javax.inject.Inject

class GetCollectionItemsUseCase @Inject constructor(
    private val repository: MarketplaceRepository
) {
    suspend operator fun invoke(address: String) = repository.getCollectionItems(address)
}