package ru.technosopher.nftmarketplaceapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.technosopher.nftmarketplaceapp.marketplace.data.repository.MarketplaceRepositoryImpl
import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.MarketplaceApi
import ru.technosopher.nftmarketplaceapp.marketplace.domain.repository.MarketplaceRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "http://194.87.131.18"

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
}