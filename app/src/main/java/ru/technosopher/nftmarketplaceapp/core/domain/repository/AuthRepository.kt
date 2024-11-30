package ru.technosopher.nftmarketplaceapp.core.domain.repository


interface AuthRepository {
    suspend fun saveSessionId(sessionId: String)
    suspend fun getSessionId(): String?

    suspend fun saveWalletAddress(walletAddress: String)
    suspend fun getWalletAddress(): String?
}