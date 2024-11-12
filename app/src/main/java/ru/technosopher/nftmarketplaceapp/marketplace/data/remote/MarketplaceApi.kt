package ru.technosopher.nftmarketplaceapp.marketplace.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto.NftCollectionDto
import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto.NftItemDto

interface MarketplaceApi {
    @GET("/nfts/{nft_address}")
    suspend fun getNft(@Path("nft_address") nftAddress : String) : NftItemDto

    @GET("/nfts/collections/{collection_address}")
    suspend fun getCollection(@Path("collection_address") collectionAddress: String) : NftCollectionDto

}