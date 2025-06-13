package com.terraplanistas.rolltogo.data.database.dao.misc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.misc.BackgroundEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BackgroundDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBackground(background: BackgroundEntity)

    @Update
    suspend fun updateBackground(background: BackgroundEntity)

    @Delete
    suspend fun deleteBackground(background: BackgroundEntity)

    @Query("SELECT * FROM background WHERE id = :backgroundId")
    fun getBackgroundById(backgroundId: String): Flow<BackgroundEntity?>

    @Query("SELECT * FROM background WHERE name LIKE :searchQuery || '%'")
    fun searchBackgroundsByName(searchQuery: String): Flow<List<BackgroundEntity>>

    @Query("SELECT * FROM background")
    fun getAllBackgrounds(): Flow<List<BackgroundEntity>>
}