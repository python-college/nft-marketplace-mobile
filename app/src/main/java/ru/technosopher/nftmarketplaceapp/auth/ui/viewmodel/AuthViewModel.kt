package ru.technosopher.nftmarketplaceapp.auth.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.technosopher.nftmarketplaceapp.auth.domain.entities.AuthErrorEntity
import ru.technosopher.nftmarketplaceapp.auth.domain.entities.AuthLinkEntity
import ru.technosopher.nftmarketplaceapp.auth.domain.entities.AuthRejectedEntity
import ru.technosopher.nftmarketplaceapp.auth.domain.entities.AuthSuccessEntity
import ru.technosopher.nftmarketplaceapp.auth.domain.usecase.ConnectToWebSocketUseCase
import ru.technosopher.nftmarketplaceapp.auth.domain.usecase.ObserveAuthErrorUseCase
import ru.technosopher.nftmarketplaceapp.auth.domain.usecase.ObserveAuthLinkUseCase
import ru.technosopher.nftmarketplaceapp.auth.domain.usecase.ObserveAuthRejectUseCase
import ru.technosopher.nftmarketplaceapp.auth.domain.usecase.ObserveAuthSuccessUseCase
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val connectToWebSocketUseCase: ConnectToWebSocketUseCase,
    private val observeAuthLinkUseCase: ObserveAuthLinkUseCase,
    private val observeAuthSuccessUseCase: ObserveAuthSuccessUseCase,
    private val observeAuthRejectUseCase: ObserveAuthRejectUseCase,
    private val observeAuthErrorUseCase: ObserveAuthErrorUseCase
): ViewModel() {
    public val TAG: String = "AUTH_VIEWMODEL"

    private val mutableAuthLinkLiveData by lazy {
        MutableLiveData<AuthLinkEntity>()
    }
    val authLinkLiveData: LiveData<AuthLinkEntity> = mutableAuthLinkLiveData

    private val mutableSuccessLiveData by lazy {
        MutableLiveData<AuthSuccessEntity>()
    }
    val successLiveData: LiveData<AuthSuccessEntity> = mutableSuccessLiveData

    private val mutableRejectLiveData by lazy {
        MutableLiveData<AuthRejectedEntity>()
    }
    val rejectLiveData: LiveData<AuthRejectedEntity> = mutableRejectLiveData

    private val mutableErrorLiveData by lazy {
        MutableLiveData<AuthErrorEntity>()
    }
    val errorLiveData: LiveData<AuthErrorEntity> = mutableErrorLiveData

    init {
        viewModelScope.launch {
            launch {
                observeAuthLinkUseCase.invoke().collect {
                    mutableAuthLinkLiveData.postValue(it)
                }
            }

            launch {
                observeAuthSuccessUseCase.invoke().collect {
                    mutableSuccessLiveData.postValue(it)
                }
            }

            launch {
                observeAuthRejectUseCase.invoke().collect {
                    mutableRejectLiveData.postValue(it)
                }
            }

            launch {
                observeAuthErrorUseCase.invoke().collect {
                    mutableErrorLiveData.postValue(it)
                }
            }
        }
    }

    fun auth() = viewModelScope.launch {
        Log.d(TAG, "Auth launched!")
        launch {
            connectToWebSocketUseCase.invoke()
        }
    }

}