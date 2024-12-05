package ru.technosopher.nftmarketplaceapp.marketplace.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto.NftCollectionDto
import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto.NftCollectionTopDto
import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto.NftItemDto
import ru.technosopher.nftmarketplaceapp.marketplace.data.remote.dto.NftItemListDto

interface MarketplaceApi {
    @GET("/content/api/v1/nfts/{nft_address}")
    suspend fun getNft(@Path("nft_address") nftAddress : String) : NftItemDto

    @GET("/content/api/v1/nfts/collections/{collection_address}")
    suspend fun getCollection(@Path("collection_address") collectionAddress: String) : NftCollectionDto

    @GET("/content/api/v1/nfts/collections/{collection_address}/items")
    suspend fun getCollectionItems(@Path("collection_address") collectionAddress: String) : NftItemListDto

    @GET("/content/api/v1/top/collections")
    suspend fun getMostHypeCollections(@Query("page") page: String,
                                       @Query("page_size") pageSize: String): NftCollectionTopDto

}