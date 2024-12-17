package ru.technosopher.nftmarketplaceapp.auth.domain.usecase

import ru.technosopher.nftmarketplaceapp.auth.domain.repository.AuthRepository
import javax.inject.Inject

class ConnectToWebSocketUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.connectToWebSocket()
}