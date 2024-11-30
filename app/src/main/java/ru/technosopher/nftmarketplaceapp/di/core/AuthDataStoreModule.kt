package ru.technosopher.nftmarketplaceapp.di.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.technosopher.nftmarketplaceapp.core.data.repository.AuthRepositoryImpl
import ru.technosopher.nftmarketplaceapp.core.domain.repository.AuthRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthDataStoreModule {

    private const val AUTH_DATASTORE_NAME = "AUTH_PREFS"

    private val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(name = AUTH_DATASTORE_NAME)

    @Provides
    @Named("sessionIdKey")
    fun provideSessionIdKey(): Preferences.Key<String> {
        return stringPreferencesKey("SESSION_ID")
    }

    @Provides
    @Named("walletAddressKey")
    fun provideWalletAddressKey(): Preferences.Key<String> {
        return stringPreferencesKey("WALLET_ADDRESS")
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.authDataStore
    }

    @Provides
    @Singleton
    fun provideAuthRepository(dataStore: DataStore<Preferences>):AuthRepository =
        AuthRepositoryImpl(dataStore, sessionIdKey = provideSessionIdKey(), walletAddressKey =  provideWalletAddressKey())
}