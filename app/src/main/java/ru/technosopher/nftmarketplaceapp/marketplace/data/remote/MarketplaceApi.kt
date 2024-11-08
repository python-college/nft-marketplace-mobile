package ru.technosopher.nftmarketplaceapp.marketplace.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto.NftDto

interface MarketplaceApi {
    @GET("/nfts/{nftAddress}")
    suspend fun getNft(@Path("nftAddress") nftAddress : String) : NftDto

}