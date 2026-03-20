package com.artcapone.steamlibrary.domain

import com.artcapone.steamlibrary.data.model.UserGameEntry
import com.artcapone.steamlibrary.data.repository.LibraryRepositoryV2

class SaveGameEntryUseCase(
    private val repository: LibraryRepositoryV2
) {
    suspend operator fun invoke(entry: UserGameEntry) {
        repository.saveEntry(entry)
    }
}
