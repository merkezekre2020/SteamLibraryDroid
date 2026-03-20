package com.artcapone.steamlibrary.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.artcapone.steamlibrary.data.local.entity.UserGameEntryEntity

@Dao
interface UserGameEntryDao {
    @Query("SELECT * FROM user_game_entries")
    suspend fun getAll(): List<UserGameEntryEntity>

    @Query("SELECT * FROM user_game_entries WHERE gameId = :gameId LIMIT 1")
    suspend fun getByGameId(gameId: Long): UserGameEntryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: UserGameEntryEntity)
}
