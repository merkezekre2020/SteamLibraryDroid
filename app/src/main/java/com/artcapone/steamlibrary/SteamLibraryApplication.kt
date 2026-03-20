package com.artcapone.steamlibrary

import android.app.Application
import com.artcapone.steamlibrary.data.AppContainer

class SteamLibraryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppContainer.init(this)
    }
}
