package ru.technosopher.nftmarketplaceapp.marketplace.domain.usecase

import ru.technosopher.nftmarketplaceapp.marketplace.domain.repository.MarketplaceRepository
import javax.inject.Inject

class GetNftUseCase @Inject constructor(
    private val repository: MarketplaceRepository
) {
    suspend operator fun invoke(nftAddress: String) = repository.getNft(nftAddress)
}