package ru.technosopher.nftmarketplaceapp.marketplace.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.technosopher.nftmarketplaceapp.R
import ru.technosopher.nftmarketplaceapp.databinding.FragmentMarketplaceBinding
import ru.technosopher.nftmarketplaceapp.marketplace.ui.adapter.NftCollectionAdapter
import ru.technosopher.nftmarketplaceapp.marketplace.ui.viewmodel.MarketplaceViewModel

@AndroidEntryPoint
class MarketplaceFragment : Fragment() {

    public val TAG : String = "MARKETPLACE_FRAGMENT"

    private var _binding: FragmentMarketplaceBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MarketplaceFragment()
        const val COLLECTION_BUNDLE = "COLLECTION"
    }

    private val viewModel: MarketplaceViewModel by viewModels()
    private lateinit var nftCollectionAdapter: NftCollectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarketplaceBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        subscribe()
    }

    private fun subscribe() {
        viewModel.stateLiveData.observe(viewLifecycleOwner, Observer<MarketplaceViewModel.State> {
            value ->
            binding.loadingBar.visibility = if (value.isLoading) View.VISIBLE else View.GONE
            binding.pageContent.visibility = if (!value.isLoading && value.data != null) View.VISIBLE else View.GONE

            if (value.data == null) {
                // Error message here
                // TODO: Error handling

            } else  {
                // Recycler here
                nftCollectionAdapter.differ.submitList(value.data)
            }

        })
    }

    private fun initUI() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        nftCollectionAdapter = NftCollectionAdapter()
        nftCollectionAdapter.setOnItemClickListener {

            val bundle = Bundle().apply {
                putParcelable(COLLECTION_BUNDLE, it)
            }

            findNavController().navigate(
                R.id.action_marketplaceFragment_to_collectionFragment,
                bundle
            )
        }
        binding.rvCollections.apply {
            adapter = nftCollectionAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}