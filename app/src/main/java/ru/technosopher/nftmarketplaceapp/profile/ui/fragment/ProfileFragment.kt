package ru.technosopher.nftmarketplaceapp.profile.ui.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import ru.technosopher.nftmarketplaceapp.MainActivity
import ru.technosopher.nftmarketplaceapp.R
import ru.technosopher.nftmarketplaceapp.databinding.FragmentNftBinding
import ru.technosopher.nftmarketplaceapp.databinding.FragmentProfileBinding
import ru.technosopher.nftmarketplaceapp.profile.ui.viewmodel.ProfileViewModel

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ProfileFragment()
        public const val TAG = "PROFILE_FRAGMENT"
    }

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnExit.setOnClickListener {
            viewModel.exit()
        }

        subscribe()
    }

    private fun subscribe() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) {value ->
            binding.loadingBar.visibility = if (value.isLoading) View.VISIBLE else View.GONE
            binding.pageContent.visibility = if (value.isLoading) View.GONE else View.VISIBLE

            if (value.walletAddress != null) {
                binding.walletAddress = value.walletAddress
                binding.executePendingBindings()
            }
        }
        viewModel.exitLiveData.observe(viewLifecycleOwner) {
            Log.d(TAG, "Exit!")
            onExit()
        }
    }

    private fun onExit() = (activity as? MainActivity)?.onExit()
}