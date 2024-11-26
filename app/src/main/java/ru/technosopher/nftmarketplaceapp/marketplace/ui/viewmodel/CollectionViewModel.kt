package ru.technosopher.nftmarketplaceapp.marketplace.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftCollectionEntity
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftEntity
import ru.technosopher.nftmarketplaceapp.marketplace.domain.usecase.GetCollectionItemsUseCase
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val getCollectionItemsUseCase: GetCollectionItemsUseCase
): ViewModel() {
    public val TAG : String = "COLLECTION_VIEWMODEL"

    private val mutableNftListStateLiveData: MutableLiveData<NftListState> by lazy {
        MutableLiveData<NftListState>()
    }
    val nftListStateLiveData : LiveData<NftListState> = mutableNftListStateLiveData

    private val mutableCollectionStateLiveData: MutableLiveData<CollectionState> by lazy {
        MutableLiveData<CollectionState>()
    }
    val collectionStateLiveData: LiveData<CollectionState> = mutableCollectionStateLiveData

    init {
        viewModelScope.launch {

        }
    }

    public fun onCreate(nftCollection: NftCollectionEntity?) {
        Log.d(TAG, "${mutableNftListStateLiveData.value}")
        mutableCollectionStateLiveData.postValue(CollectionState(
            nftCollection,
            if (nftCollection == null) "Unkwown error" else null
        ))
        if (mutableNftListStateLiveData.value != null) {
            mutableNftListStateLiveData.postValue(mutableNftListStateLiveData.value)
        } else {
            getCollectionItems(collectionAddress = nftCollection?.address)
        }
    }

    private fun getCollectionItems(collectionAddress: String?) = viewModelScope.launch {
        mutableNftListStateLiveData.postValue(NftListState(
            data = null,
            isLoading = true,
            errorMessage = null
        ))
        if (collectionAddress != null) {
            val response = getCollectionItemsUseCase.invoke(collectionAddress)
            Log.d(TAG, "Response is Right : ${response.isRight()}")
            Log.d(TAG, "Response is Left : ${response.isLeft()}")
            // TODO: Error handling
            if (response.isLeft()) {
                val left = response.leftOrNull()
                if (left != null) {
                    Log.d(TAG, left.throwable.toString())
                }
            } else {
                val data = response.getOrNull()
                if (data != null) {
                    mutableNftListStateLiveData.postValue(
                        NftListState(
                            data = data,
                            isLoading = false,
                            errorMessage = null
                        )
                    )
                }
            }
        }
    }

    data class CollectionState(
        val data: NftCollectionEntity?,
        val errorMessage: String?
    )

    data class NftListState(
        val data: List<NftEntity>?,
        val isLoading: Boolean,
        val errorMessage: String?
    )
}