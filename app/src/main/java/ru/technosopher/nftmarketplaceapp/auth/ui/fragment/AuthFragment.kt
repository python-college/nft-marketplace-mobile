package ru.technosopher.nftmarketplaceapp.auth.ui.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.technosopher.nftmarketplaceapp.MainActivity
import ru.technosopher.nftmarketplaceapp.R
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
            // TODO: Remove skip, it is developer button
            onAuthenticationSuccess()
        }
    }

    private fun subscribe() {
        viewModel.authLinkLiveData.observe(viewLifecycleOwner) { value ->
            if (value != null) {
                Log.d(TAG, "Auth link: ${value.url}")

                val deepLink = Uri.parse(value.url)
                val webIntent: Intent = Intent(Intent.ACTION_VIEW, deepLink)
                try {
                    startActivity(webIntent)
                } catch (e: ActivityNotFoundException) {
                    Log.d(TAG, "$e")
                }
            }
        }

        viewModel.authenticatedLiveData.observe(viewLifecycleOwner) { value ->
            if (value != null && value != "") {
                onAuthenticationSuccess()
            } else {
                binding.loadingBar.visibility = View.GONE
                binding.pageContent.visibility = View.VISIBLE
            }
        }

        viewModel.successLiveData.observe(viewLifecycleOwner) { value ->
            Log.d(TAG, "Success auth!\nAddress: ${value.address} \nSessionID: ${value.sessionId}")
            onAuthenticationSuccess()
        }

        viewModel.rejectLiveData.observe(viewLifecycleOwner) {
            Log.d(TAG, "User rejected auth")
            Snackbar.make(binding.root, getString(R.string.authorization_denied), Snackbar.LENGTH_SHORT).show()
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Log.d(TAG, "Auth error")
            Snackbar.make(binding.root, getString(R.string.error_occurred), Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun onAuthenticationSuccess() = (activity as? MainActivity)?.onAuthenticationSuccess()
}