package com.terraplanistas.rolltogo.data.database.dao.features

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.features.LevelProgressionsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelProgressionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLevelProgression(levelProgression: LevelProgressionsEntity)

    @Update
    suspend fun updateLevelProgression(levelProgression: LevelProgressionsEntity)

    @Delete
    suspend fun deleteLevelProgression(levelProgression: LevelProgressionsEntity)

    @Query("SELECT * FROM level_progressions WHERE id = :levelProgressionId")
    fun getLevelProgressionById(levelProgressionId: String): Flow<LevelProgressionsEntity?>

    @Query("SELECT * FROM level_progressions WHERE level = :level")
    fun getLevelProgressionsByLevel(level: Int): Flow<List<LevelProgressionsEntity>>

    @Query("SELECT * FROM level_progressions WHERE level_progression_type = :type")
    fun getLevelProgressionsByType(type: String): Flow<List<LevelProgressionsEntity>>

    @Query("SELECT * FROM level_progressions WHERE grant_id = :grantId")
    fun getLevelProgressionsByGrantId(grantId: String): Flow<List<LevelProgressionsEntity>>

    @Query("SELECT * FROM level_progressions WHERE grant_option_set_id = :grantOptionSetId")
    fun getLevelProgressionsByGrantOptionSetId(grantOptionSetId: String): Flow<List<LevelProgressionsEntity>>

    @Query("SELECT * FROM level_progressions")
    fun getAllLevelProgressions(): Flow<List<LevelProgressionsEntity>>
}