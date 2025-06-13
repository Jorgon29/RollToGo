package com.terraplanistas.rolltogo.data.database.dao.features

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.features.LevelProgressionsEntity
import com.terraplanistas.rolltogo.data.database.entities.features.SpecialDieEntity
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantOptionSetsEntity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.features.SpecialDieProgressionsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpecialDieProgressionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecialDieProgression(specialDieProgression: SpecialDieProgressionsEntity)

    @Update
    suspend fun updateSpecialDieProgression(specialDieProgression: SpecialDieProgressionsEntity)

    @Delete
    suspend fun deleteSpecialDieProgression(specialDieProgression: SpecialDieProgressionsEntity)

    @Query("SELECT * FROM special_die_progressions WHERE id = :specialDieProgressionId")
    fun getSpecialDieProgressionById(specialDieProgressionId: String): Flow<SpecialDieProgressionsEntity?>

    @Query("SELECT * FROM special_die_progressions WHERE special_die_id = :specialDieId")
    fun getSpecialDieProgressionsBySpecialDieId(specialDieId: String): Flow<List<SpecialDieProgressionsEntity>>

    @Query("SELECT * FROM special_die_progressions WHERE level_progressions = :levelProgressionId")
    fun getSpecialDieProgressionsByLevelProgressionId(levelProgressionId: String): Flow<List<SpecialDieProgressionsEntity>>

    @Query("SELECT * FROM special_die_progressions WHERE grant_option_set_id = :grantOptionSetId")
    fun getSpecialDieProgressionsByGrantOptionSetId(grantOptionSetId: String): Flow<List<SpecialDieProgressionsEntity>>

    @Query("SELECT * FROM special_die_progressions")
    fun getAllSpecialDieProgressions(): Flow<List<SpecialDieProgressionsEntity>>
}