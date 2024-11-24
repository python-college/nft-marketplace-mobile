package ru.technosopher.nftmarketplaceapp.marketplace.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    public fun update() {
        mutableStateLiveData.postValue(State(
            isLoading = true,
            errorMessage = null
        ))

        mutableStateLiveData.postValue(State(
            isLoading = false,
            errorMessage = null
        ))
    }

    data class State(
        val isLoading: Boolean,
        val errorMessage: String?
    )
}