package ru.technosopher.nftmarketplaceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.technosopher.nftmarketplaceapp.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener {_, destination, _ ->
            when (destination.id) {
                R.id.authFragment -> {
                    binding.bottomNavBar.visibility = View.GONE
                }
                else -> {
                    binding.bottomNavBar.visibility = View.VISIBLE
                }
            }
        }

        binding.bottomNavBar.setOnItemSelectedListener { id ->
//            val option = when (id) {
//                R.id.marketplaceFragment -> {
//                    navController.navigate(R.id.marketplaceFragment)
//                    true
//                }
//                R.id.searchFragment -> {
//                    navController.navigate(R.id.searchFragment)
//                    true
//                }
//                R.id.profileFragment -> {
//                    navController.navigate(R.id.profileFragment)
//                    true
//                }
//                else -> false
//            }
            val destination = when (id) {
                R.id.marketplaceFragment -> R.id.marketplaceFragment
                R.id.searchFragment -> R.id.searchFragment
                R.id.profileFragment -> R.id.profileFragment
                else -> return@setOnItemSelectedListener
            }

            // FIXME: Иди своей дорогой сталкер. Это костыль, который позволяет по нажатию кнопки "назад" в фрагментах,
            //  которые должны быть top-level destination, выходить из приложения, а не возвращаться на предыдущий экран
            //  Это можно пофиксить, получая id предыдущего фрагмента и чистить backstack до него
            while (navController.popBackStack()) {}

            navController.navigate(destination)
        }

    }

    fun onAuthenticationSuccess() {
        navController.navigate(R.id.marketplaceFragment)

        binding.bottomNavBar.setItemSelected(R.id.marketplaceFragment, true)
    }

}