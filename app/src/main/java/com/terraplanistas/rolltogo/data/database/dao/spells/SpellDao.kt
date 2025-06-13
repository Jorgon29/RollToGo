package com.terraplanistas.rolltogo.data.database.dao.spells

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.spells.SpellEntity
import com.terraplanistas.rolltogo.data.enums.SpellSchoolEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface SpellDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpell(spell: SpellEntity)

    @Update
    suspend fun updateSpell(spell: SpellEntity)

    @Delete
    suspend fun deleteSpell(spell: SpellEntity)

    @Query("SELECT * FROM spell WHERE id = :spellId")
    fun getSpellById(spellId: String): Flow<SpellEntity?>

    @Query("SELECT * FROM spell WHERE name LIKE :searchQuery || '%'")
    fun searchSpellsByName(searchQuery: String): Flow<List<SpellEntity>>

    @Query("SELECT * FROM spell WHERE spell_level_enum = :level")
    fun getSpellsByLevel(level: Int): Flow<List<SpellEntity>>

    @Query("SELECT * FROM spell WHERE spell_school_enum = :school")
    fun getSpellsBySchool(school: SpellSchoolEnum): Flow<List<SpellEntity>>

    @Query("SELECT * FROM spell WHERE preparation_mode_enum = :mode")
    fun getSpellsByPreparationMode(mode: String): Flow<List<SpellEntity>>

    @Query("SELECT * FROM spell")
    fun getAllSpells(): Flow<List<SpellEntity>>
}