package ru.technosopher.nftmarketplaceapp.marketplace.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftCollectionEntity
import ru.technosopher.nftmarketplaceapp.marketplace.domain.usecase.GetCollectionListUseCase
import javax.inject.Inject

@HiltViewModel
class MarketplaceViewModel @Inject constructor(
    private val getCollectionListUseCase: GetCollectionListUseCase
) : ViewModel() {

    public val TAG : String = "MARKETPLACE_VIEWMODEL"

    private val mutableStateLiveData: MutableLiveData<State> by lazy {
        MutableLiveData<State>()
    }
    val stateLiveData : LiveData<State> = mutableStateLiveData

    init {
        viewModelScope.launch {
            getCollections()
        }
    }

    fun getCollections() = viewModelScope.launch {
        mutableStateLiveData.postValue(State(
            data = null,
            isLoading = true,
            errorMessage = null
        )
        )
        val response = getCollectionListUseCase.invoke()
        Log.d(TAG, "Response is Right : ${response.isRight()}")
        Log.d(TAG, "Response is Left : ${response.isLeft()}")
        // TODO: Error handling
        if (response.isLeft()) {
            val left = response.leftOrNull()
            if (left != null) {
                Log.d(TAG, left.throwable.toString())
            }
        }
        val data = response.getOrNull()
        if (data != null) {
            mutableStateLiveData.postValue(State(
                data = data,
                isLoading = false,
                errorMessage = null
            ))
        }

    }

    data class State(
        val data: List<NftCollectionEntity>?,
        val isLoading: Boolean,
        val errorMessage: String?
    )

}