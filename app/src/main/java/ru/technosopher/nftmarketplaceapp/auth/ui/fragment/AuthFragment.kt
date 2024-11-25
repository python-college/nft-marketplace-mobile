package ru.technosopher.nftmarketplaceapp.auth.ui.fragment

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
import ru.technosopher.nftmarketplaceapp.auth.domain.entities.AuthLinkEntity
import ru.technosopher.nftmarketplaceapp.auth.domain.entities.AuthSuccessEntity
import ru.technosopher.nftmarketplaceapp.auth.ui.viewmodel.AuthViewModel
import ru.technosopher.nftmarketplaceapp.databinding.FragmentAuthBinding

@AndroidEntryPoint
class AuthFragment : Fragment() {

    public val TAG: String = "AUTH_FRAGMENT"

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = AuthFragment()
    }

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        subscribe()
    }

    private fun initUI() {
        binding.btnConnect.setOnClickListener {
            viewModel.auth()
        }
        binding.tvSkip.setOnClickListener {
            findNavController().navigate(
                R.id.action_authFragment_to_marketplaceFragment
            )
        }
    }

    private fun subscribe() {
        viewModel.authLinkLiveData.observe(viewLifecycleOwner, Observer<AuthLinkEntity> { value ->
            Log.d(TAG, "Auth link: ${value.url}")
            binding.tvDevInfo.text = value.url
        })

        viewModel.successLiveData.observe(viewLifecycleOwner, Observer<AuthSuccessEntity> { value ->
            Log.d(TAG, "Success auth!\nAddress: ${value.address} \nSessionID: ${value.sessionId}")
            binding.tvDevInfo.text = value.sessionId
        })
    }
}