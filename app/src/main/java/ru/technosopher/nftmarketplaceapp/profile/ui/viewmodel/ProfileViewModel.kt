package ru.technosopher.nftmarketplaceapp.profile.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.technosopher.nftmarketplaceapp.core.domain.usecase.DeleteSessionIdUseCase
import ru.technosopher.nftmarketplaceapp.core.domain.usecase.DeleteWalletAddressUseCase
import ru.technosopher.nftmarketplaceapp.core.domain.usecase.GetWalletAddressUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getWalletAddressUseCase: GetWalletAddressUseCase,
    private val deleteWalletAddressUseCase: DeleteWalletAddressUseCase,
    private val deleteSessionIdUseCase: DeleteSessionIdUseCase
) : ViewModel() {
    private val mutableStateLiveData: MutableLiveData<State> by lazy {
        MutableLiveData<State>()
    }
    public val stateLiveData: LiveData<State> = mutableStateLiveData

    private val mutableExitLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    public val exitLiveData: LiveData<String> = mutableExitLiveData

    init {
        viewModelScope.launch {
            getWalletAddress()
        }
    }

    private fun getWalletAddress() = viewModelScope.launch {
        mutableStateLiveData.postValue(
            State(
            walletAddress = null,
            isLoading = true,
            errorMessage = null,
        ))
        val walletAddress = getWalletAddressUseCase.invoke()
        mutableStateLiveData.postValue(State(
            walletAddress = walletAddress,
            isLoading = false,
            errorMessage = null
        ))
    }

    fun exit() = viewModelScope.launch {
        runBlocking {
            deleteSessionIdUseCase.invoke()
            deleteWalletAddressUseCase.invoke()
        }
        mutableExitLiveData.postValue("")
    }

    data class State(
        val walletAddress: String?,
        val isLoading: Boolean,
        val errorMessage: String?
    )
}