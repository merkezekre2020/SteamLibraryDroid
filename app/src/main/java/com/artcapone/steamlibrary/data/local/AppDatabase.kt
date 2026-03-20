package com.artcapone.steamlibrary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.artcapone.steamlibrary.data.local.dao.GameDao
import com.artcapone.steamlibrary.data.local.dao.UserGameEntryDao
import com.artcapone.steamlibrary.data.local.entity.GameEntity
import com.artcapone.steamlibrary.data.local.entity.UserGameEntryEntity

@Database(
    entities = [GameEntity::class, UserGameEntryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun userGameEntryDao(): UserGameEntryDao
}
