package ru.technosopher.nftmarketplaceapp.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.firstOrNull
import ru.technosopher.nftmarketplaceapp.core.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Named

class AuthRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    @Named("sessionIdKey") private val sessionIdKey: Preferences.Key<String>,
    @Named("walletAddressKey") private val walletAddressKey: Preferences.Key<String>
): AuthRepository {
    override suspend fun saveSessionId(sessionId: String) {
        dataStore.edit { prefs ->
            prefs[sessionIdKey] = sessionId
        }
    }

    override suspend fun getSessionId(): String? {
        val preferences = dataStore.data.firstOrNull()
        return preferences?.get(sessionIdKey)
    }

    override suspend fun saveWalletAddress(walletAddress: String) {
        dataStore.edit { prefs ->
            prefs[walletAddressKey] = walletAddress
        }
    }

    override suspend fun getWalletAddress(): String? {
        val preferences = dataStore.data.firstOrNull()
        return preferences?.get(walletAddressKey)
    }

    override suspend fun deleteSessionId() {
        dataStore.edit { prefs ->
            prefs[sessionIdKey] = ""
        }
    }

    override suspend fun deleteWalletAddress() {
        dataStore.edit { prefs ->
            prefs[walletAddressKey] = ""
        }
    }
}