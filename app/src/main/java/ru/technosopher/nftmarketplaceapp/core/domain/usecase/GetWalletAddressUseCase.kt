package ru.technosopher.nftmarketplaceapp.core.domain.usecase

import ru.technosopher.nftmarketplaceapp.core.domain.repository.AuthRepository
import javax.inject.Inject

class GetWalletAddressUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.getWalletAddress()
}