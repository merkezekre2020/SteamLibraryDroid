package com.artcapone.steamlibrary.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.artcapone.steamlibrary.data.local.entity.GameEntity

@Dao
interface GameDao {
    @Query("SELECT * FROM games ORDER BY name ASC")
    suspend fun getAll(): List<GameEntity>

    @Query("SELECT * FROM games WHERE appId = :appId LIMIT 1")
    suspend fun getById(appId: Long): GameEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<GameEntity>)

    @Query("DELETE FROM games")
    suspend fun clearAll()
}
