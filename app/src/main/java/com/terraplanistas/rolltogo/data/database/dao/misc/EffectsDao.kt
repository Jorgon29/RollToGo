package com.terraplanistas.rolltogo.data.database.dao.misc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.misc.EffectsEntity
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface EffectsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEffect(effect: EffectsEntity)

    @Update
    suspend fun updateEffect(effect: EffectsEntity)

    @Delete
    suspend fun deleteEffect(effect: EffectsEntity)

    @Query("SELECT * FROM effects WHERE id = :effectId")
    fun getEffectById(effectId: String): Flow<EffectsEntity?>

    @Query("SELECT * FROM effects WHERE name LIKE :searchQuery || '%'")
    fun searchEffectsByName(searchQuery: String): Flow<List<EffectsEntity>>

    @Query("SELECT * FROM effects WHERE condition_enum = :condition")
    fun getEffectsByCondition(condition: String): Flow<List<EffectsEntity>>

    @Query("SELECT * FROM effects WHERE duration_unit = :durationUnit")
    fun getEffectsByDurationUnit(durationUnit: DurationUnitEnum): Flow<List<EffectsEntity>>

    @Query("SELECT * FROM effects")
    fun getAllEffects(): Flow<List<EffectsEntity>>
}