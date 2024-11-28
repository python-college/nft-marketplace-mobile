package ru.technosopher.nftmarketplaceapp.auth.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.technosopher.nftmarketplaceapp.auth.domain.entities.AuthErrorEntity
import ru.technosopher.nftmarketplaceapp.auth.domain.repository.AuthRepository
import javax.inject.Inject

class ObserveAuthErrorUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Flow<AuthErrorEntity> = repository.observeAuthError()
}