package com.terraplanistas.rolltogo.data.database.dao.classDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.classEntity.SpellcastingEntity
import com.terraplanistas.rolltogo.data.enums.SpellcastingProgressionEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface SpellcastingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpellcasting(spellcasting: SpellcastingEntity)

    @Update
    suspend fun updateSpellcasting(spellcasting: SpellcastingEntity)

    @Delete
    suspend fun deleteSpellcasting(spellcasting: SpellcastingEntity)

    @Query("SELECT * FROM spellcasting WHERE id = :spellcastingId")
    fun getSpellcastingById(spellcastingId: String): Flow<SpellcastingEntity?>

    @Query("SELECT * FROM spellcasting WHERE class_id = :classId")
    fun getSpellcastingByClassId(classId: String): Flow<List<SpellcastingEntity>>

    @Query("SELECT * FROM spellcasting WHERE spell_progression_enum = :progression")
    fun getSpellcastingByProgression(progression: SpellcastingProgressionEnum): Flow<List<SpellcastingEntity>>

    @Query("SELECT * FROM spellcasting")
    fun getAllSpellcasting(): Flow<List<SpellcastingEntity>>
}