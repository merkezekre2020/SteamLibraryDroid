package com.artcapone.steamlibrary.data

import android.content.Context
import com.artcapone.steamlibrary.data.local.DatabaseProvider
import com.artcapone.steamlibrary.data.remote.PocketBaseClient
import com.artcapone.steamlibrary.data.remote.SteamImporter
import com.artcapone.steamlibrary.data.repository.AuthRepository
import com.artcapone.steamlibrary.data.repository.LibraryRepository
import com.artcapone.steamlibrary.data.repository.LibraryRepositoryV2
import com.artcapone.steamlibrary.domain.GetCachedLibraryUseCase
import com.artcapone.steamlibrary.domain.SaveGameEntryUseCase
import com.artcapone.steamlibrary.domain.SyncLibraryUseCase

object AppContainer {
    private var initialized = false

    lateinit var authRepository: AuthRepository
        private set

    lateinit var libraryRepository: LibraryRepository
        private set

    lateinit var libraryRepositoryV2: LibraryRepositoryV2
        private set

    lateinit var pocketBaseClient: PocketBaseClient
        private set

    lateinit var steamImporter: SteamImporter
        private set

    lateinit var syncLibraryUseCase: SyncLibraryUseCase
        private set

    lateinit var getCachedLibraryUseCase: GetCachedLibraryUseCase
        private set

    lateinit var saveGameEntryUseCase: SaveGameEntryUseCase
        private set

    fun init(context: Context) {
        if (initialized) return

        val db = DatabaseProvider.get(context)

        authRepository = AuthRepository()
        libraryRepository = LibraryRepository()
        steamImporter = SteamImporter()
        pocketBaseClient = PocketBaseClient()
        libraryRepositoryV2 = LibraryRepositoryV2(
            gameDao = db.gameDao(),
            userGameEntryDao = db.userGameEntryDao(),
            steamImporter = steamImporter
        )
        syncLibraryUseCase = SyncLibraryUseCase(libraryRepositoryV2)
        getCachedLibraryUseCase = GetCachedLibraryUseCase(libraryRepositoryV2)
        saveGameEntryUseCase = SaveGameEntryUseCase(libraryRepositoryV2)

        initialized = true
    }
}
