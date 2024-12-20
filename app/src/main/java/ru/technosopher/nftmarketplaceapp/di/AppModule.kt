package ru.technosopher.nftmarketplaceapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.technosopher.nftmarketplaceapp.auth.data.repository.AuthRepositoryImpl
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.AuthWebSocketClient
import ru.technosopher.nftmarketplaceapp.auth.domain.repository.AuthRepository
import ru.technosopher.nftmarketplaceapp.marketplace.data.repository.MarketplaceRepositoryImpl
import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.MarketplaceApi
import ru.technosopher.nftmarketplaceapp.marketplace.domain.repository.MarketplaceRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_IP_ADDRESS = "api.rarebay.ru"
    private const val BASE_URL = "http://$BASE_IP_ADDRESS"
    private const val BASE_WS = "ws://$BASE_IP_ADDRESS"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    private val client : OkHttpClient.Builder = OkHttpClient().newBuilder().apply {
//        addInterceptor(
//
//        )
//    }

    @Provides
    @Singleton
    fun provideMarketplaceApi(retrofit: Retrofit) : MarketplaceApi = retrofit.create(MarketplaceApi::class.java)

    @Provides
    @Singleton
    fun provideMarketplaceRepository(api: MarketplaceApi) : MarketplaceRepository = MarketplaceRepositoryImpl(api)


    // WEBSOCKETS

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
            install(HttpTimeout) {
                requestTimeoutMillis = 10000
                connectTimeoutMillis = 10000
                socketTimeoutMillis = 10000
            }

            defaultRequest {
                url(BASE_WS)
                port = 80

            }
        }
    }

    @Provides
    @Singleton
    fun provideAuthWebSocketClient(httpClient: HttpClient) : AuthWebSocketClient = AuthWebSocketClient(httpClient)

    @Provides
    @Singleton
    fun provideAuthRepository(authWebSocketClient: AuthWebSocketClient) : AuthRepository = AuthRepositoryImpl(authWebSocketClient)
}