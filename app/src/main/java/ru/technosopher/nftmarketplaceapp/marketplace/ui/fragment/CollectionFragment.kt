package ru.technosopher.nftmarketplaceapp.marketplace.ui.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import ru.technosopher.nftmarketplaceapp.databinding.FragmentCollectionBinding
import ru.technosopher.nftmarketplaceapp.marketplace.ui.viewmodel.CollectionViewModel

@AndroidEntryPoint
class CollectionFragment : Fragment() {

    public val TAG : String = "COLLECTION_FRAGMENT"

    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = CollectionFragment()
    }

    private val viewModel: CollectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val collectionAddress = arguments?.getString("collectionAddress")
        Log.println(Log.DEBUG, TAG, "nftAddress : $collectionAddress")
        binding.tvAddress.text = collectionAddress
    }
}
