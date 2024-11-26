package ru.technosopher.nftmarketplaceapp.marketplace.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftEntity
import javax.inject.Inject


@HiltViewModel
class NftViewModel @Inject constructor(

) : ViewModel() {

    public val TAG : String = "MARKETPLACE_VIEWMODEL"

    private val mutableStateLiveData: MutableLiveData<State> by lazy {
        MutableLiveData<State>()
    }
    public val stateLiveData: LiveData<State> = mutableStateLiveData

    init {
        viewModelScope.launch {

        }
    }

    public fun onCreate(nftEntity: NftEntity?, collectionImageUrl: String?) {
        mutableStateLiveData.postValue(State(
            nft = null,
            collectionImageUrl = null,
            isLoading = true,
            errorMessage = null
        ))
        mutableStateLiveData.postValue(State(
            nft = nftEntity,
            collectionImageUrl = collectionImageUrl,
            isLoading = false,
            errorMessage = null
        ))
    }

    data class State(
        val nft: NftEntity?,
        val collectionImageUrl: String?,
        val isLoading: Boolean,
        val errorMessage: String?
    )
}