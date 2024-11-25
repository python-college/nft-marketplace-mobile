package ru.technosopher.nftmarketplaceapp.auth.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.technosopher.nftmarketplaceapp.auth.data.util.toEntity
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.AuthWebSocketClient
import ru.technosopher.nftmarketplaceapp.auth.domain.entities.AuthLinkEntity
import ru.technosopher.nftmarketplaceapp.auth.domain.entities.AuthSuccessEntity
import ru.technosopher.nftmarketplaceapp.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authWebSocketClient: AuthWebSocketClient
) : AuthRepository {

    override suspend fun connectToWebSocket() {
        authWebSocketClient.connect()
    }

    override suspend fun observeAuthLink(): Flow<AuthLinkEntity> {
        return authWebSocketClient.observeAuthLink()
            .map { it.toEntity() }
    }

    override suspend fun observeAuthSuccess(): Flow<AuthSuccessEntity> {
        return authWebSocketClient.observeAuthSuccess()
            .map { it.toEntity() }
    }

}