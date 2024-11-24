package ru.technosopher.nftmarketplaceapp.marketplace.ui.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.technosopher.nftmarketplaceapp.R
import ru.technosopher.nftmarketplaceapp.databinding.FragmentCollectionBinding
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftCollectionEntity
import ru.technosopher.nftmarketplaceapp.marketplace.ui.adapter.NftItemAdapter
import ru.technosopher.nftmarketplaceapp.marketplace.ui.viewmodel.CollectionViewModel

@AndroidEntryPoint
class CollectionFragment : Fragment() {

    public val TAG : String = "COLLECTION_FRAGMENT"

    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = CollectionFragment()
        const val NFT_BUNDLE = "NFT"
        const val COLLECTION_IMAGE_URL_BUNDLE = "COLLECTION_IMAGE"
    }

    private val viewModel: CollectionViewModel by viewModels()
    private lateinit var nftItemAdapter: NftItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val collection : NftCollectionEntity? = arguments?.getParcelable(MarketplaceFragment.COLLECTION_BUNDLE)

        initUI(collection)
        subscribe()
        viewModel.getCollectionItems(collectionAddress = collection?.address)
    }

    private fun subscribe() {
        viewModel.stateLiveData.observe(viewLifecycleOwner, Observer<CollectionViewModel.State> {
            value ->
            binding.loadingBar.visibility = if (value.isLoading) View.VISIBLE else View.GONE

            if (value.data == null) {
                // TODO: Error handling
            } else {
                nftItemAdapter.differ.submitList(value.data)
            }
        })
    }

    private fun initUI(collection: NftCollectionEntity?) {

        Log.println(Log.DEBUG, TAG, "nftAddress : ${collection?.address}")


        binding.collection = collection
        binding.executePendingBindings()

        setupRecyclerView(collection)

    }

    private fun setupRecyclerView(collection: NftCollectionEntity?) {
        nftItemAdapter = NftItemAdapter()
        nftItemAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable(NFT_BUNDLE, it)
                putString(COLLECTION_IMAGE_URL_BUNDLE, collection?.image)
            }

            findNavController().navigate(
                R.id.action_collectionFragment_to_nftFragment,
                bundle
            )
        }
        binding.rvNftList.apply {
            adapter = nftItemAdapter
        }
    }
}
