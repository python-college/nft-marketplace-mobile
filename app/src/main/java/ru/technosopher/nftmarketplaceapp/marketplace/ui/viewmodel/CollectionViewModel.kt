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
import ru.technosopher.nftmarketplaceapp.marketplace.domain.usecase.GetCollectionUseCase
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val getCollectionItemsUseCase: GetCollectionItemsUseCase
): ViewModel() {
    public val TAG : String = "COLLECTION_VIEWMODEL"

    private val mutableStateLiveData: MutableLiveData<State> by lazy {
        MutableLiveData<State>()
    }
    val stateLiveData : LiveData<State> = mutableStateLiveData

    init {
        viewModelScope.launch {

        }
    }

    public fun getCollectionItems(collectionAddress: String?) = viewModelScope.launch {
        mutableStateLiveData.postValue(State(
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
                    mutableStateLiveData.postValue(
                        State(
                            data = data,
                            isLoading = false,
                            errorMessage = null
                        )
                    )
                }
            }
        }
    }


    data class State(
        val data: List<NftEntity>?,
        val isLoading: Boolean,
        val errorMessage: String?
    )
}