package ru.technosopher.nftmarketplaceapp.marketplace.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.technosopher.nftmarketplaceapp.R
import ru.technosopher.nftmarketplaceapp.databinding.FragmentMarketplaceBinding
import ru.technosopher.nftmarketplaceapp.marketplace.ui.adapter.NftAdapter
import ru.technosopher.nftmarketplaceapp.marketplace.ui.viewmodel.MarketplaceViewModel

@AndroidEntryPoint
class MarketplaceFragment : Fragment() {

    public val TAG : String = "MARKETPLACE_FRAGMENT"

    private var _binding: FragmentMarketplaceBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MarketplaceFragment()
    }

    private val viewModel: MarketplaceViewModel by viewModels()
    private lateinit var nftAdapter: NftAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarketplaceBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
//        binding.btnViewNft.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_marketplaceFragment_to_nftFragment,
//                bundleOf(
//                    "nftAddress" to "EQBITipAFriALvpIpAgJAfFW5TczJvxOOBpjPQPhfzQhJ43D"
//                )
//            )
//        }
        nftAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putString("collectionAddress", it.address)
            }
            findNavController().navigate(
                R.id.action_marketplaceFragment_to_collectionFragment,
                bundle
            )
        }
        subscribe()
    }

    fun subscribe() {
        viewModel.stateLiveData.observe(viewLifecycleOwner, Observer<MarketplaceViewModel.State> {
            value ->
            binding.loadingBar.visibility = if (value.isLoading) View.VISIBLE else View.GONE
            binding.pageContent.visibility = if (!value.isLoading && value.data != null) View.VISIBLE else View.GONE

            if (value.data == null) {
                // Error message here
            } else  {
                // Recycler here
                nftAdapter.differ.submitList(value.data)
            }

        })
    }

    private fun setupRecyclerView() {
        nftAdapter = NftAdapter()
        binding.rvCollections.apply {
            adapter = nftAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}