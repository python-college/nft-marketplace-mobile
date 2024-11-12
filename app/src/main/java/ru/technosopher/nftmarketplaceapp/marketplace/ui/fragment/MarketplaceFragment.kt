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
import ru.technosopher.nftmarketplaceapp.marketplace.ui.viewmodel.MarketplaceViewModel

@AndroidEntryPoint
class MarketplaceFragment : Fragment() {

    private val TAG : String = "MARKETPLACE_FRAGMENT"

    private var _binding: FragmentMarketplaceBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MarketplaceFragment()
    }

    private val viewModel: MarketplaceViewModel by viewModels<MarketplaceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarketplaceBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        binding.btnViewNft.setOnClickListener {
            findNavController().navigate(
                R.id.action_marketplaceFragment_to_nftFragment,
                bundleOf(
                    "nftAddress" to "EQBITipAFriALvpIpAgJAfFW5TczJvxOOBpjPQPhfzQhJ43D"
                )
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
                //
            } else  {
                // Recycler here
                binding.tvCollectionAddress.text = value.data.get(0).address
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}