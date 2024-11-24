package ru.technosopher.nftmarketplaceapp.marketplace.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import ru.technosopher.nftmarketplaceapp.databinding.FragmentNftBinding
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftEntity
import ru.technosopher.nftmarketplaceapp.marketplace.ui.viewmodel.NftViewModel

@AndroidEntryPoint
class NftFragment : Fragment() {

    public val TAG : String = "NFT_FRAGMENT"

    private var _binding: FragmentNftBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = NftFragment()
    }

    private val viewModel: NftViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNftBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nft: NftEntity? = arguments?.getParcelable(CollectionFragment.NFT_BUNDLE)
        val collectionImageUrl: String? = arguments?.getString(CollectionFragment.COLLECTION_IMAGE_URL_BUNDLE)


        // TODO: Use the ViewModel
        initUI(nft, collectionImageUrl)
        subscribe()
        viewModel.update()
    }

    private fun subscribe() {
        viewModel.stateLiveData.observe(viewLifecycleOwner, Observer<NftViewModel.State> {
            value ->
            binding.loadingBar.visibility = if (value.isLoading) View.VISIBLE else View.GONE
            binding.pageContent.visibility = if (value.isLoading) View.GONE else View.VISIBLE
        })
    }

    private fun initUI(nft: NftEntity?, collectionImageUrl: String?) {
        binding.nft = nft
        binding.collectionImageUrl = collectionImageUrl
        binding.executePendingBindings()
    }

}