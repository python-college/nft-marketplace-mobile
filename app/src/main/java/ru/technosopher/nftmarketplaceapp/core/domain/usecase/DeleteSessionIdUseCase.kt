package ru.technosopher.nftmarketplaceapp.core.domain.usecase

import ru.technosopher.nftmarketplaceapp.core.domain.repository.AuthRepository
import javax.inject.Inject

class DeleteSessionIdUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.deleteSessionId()
}