package ru.technosopher.nftmarketplaceapp.marketplace.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.technosopher.nftmarketplaceapp.marketplace.domain.usecase.GetCollectionUseCase
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val getCollectionUseCase: GetCollectionUseCase
): ViewModel() {
    public val TAG : String = "COLLECTION_VIEWMODEL"

    init {
        viewModelScope.launch {

        }
    }

    // fun getCollection(collectionAddress: String) TODO: Implement this
}