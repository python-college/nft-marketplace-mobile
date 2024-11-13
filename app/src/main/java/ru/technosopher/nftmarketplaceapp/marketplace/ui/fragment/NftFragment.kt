package ru.technosopher.nftmarketplaceapp.marketplace.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import ru.technosopher.nftmarketplaceapp.databinding.FragmentNftBinding
import ru.technosopher.nftmarketplaceapp.marketplace.ui.viewmodel.NftViewModel

@AndroidEntryPoint
class NftFragment : Fragment() {

    public val TAG : String = "NFT_FRAGMENT"

    private var _binding: FragmentNftBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = NftFragment()
    }

    private lateinit var viewModel: NftViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNftBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NftViewModel::class.java]

        val nftAddress = arguments?.getString("nftAddress")
        Log.println(Log.DEBUG, TAG, "nftAddress : $nftAddress")
        binding.tvAddress.text = nftAddress

        // TODO: Use the ViewModel
    }

}