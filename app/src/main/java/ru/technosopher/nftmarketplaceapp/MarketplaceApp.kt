package ru.technosopher.nftmarketplaceapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MarketplaceApp : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}