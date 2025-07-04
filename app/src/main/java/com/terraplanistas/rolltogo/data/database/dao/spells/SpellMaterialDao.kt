package com.terraplanistas.rolltogo.data.database.dao.spells

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.spells.SpellMaterialEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpellMaterialDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpellMaterial(spellMaterial: SpellMaterialEntity)

    @Update
    suspend fun updateSpellMaterial(spellMaterial: SpellMaterialEntity)

    @Delete
    suspend fun deleteSpellMaterial(spellMaterial: SpellMaterialEntity)

    @Query("SELECT * FROM spell_material WHERE spell_id = :spellId")
    fun getMaterialsForSpell(spellId: String): Flow<List<SpellMaterialEntity>>

    @Query("SELECT * FROM spell_material WHERE item_id = :itemId")
    fun getSpellsUsingMaterial(itemId: String): Flow<List<SpellMaterialEntity>>

    @Query("SELECT * FROM spell_material WHERE spell_id = :spellId AND item_id = :itemId")
    fun getSpellMaterialByIds(spellId: String, itemId: String): Flow<SpellMaterialEntity?>

    @Query("DELETE FROM spell_material WHERE spell_id = :spellId AND item_id = :itemId")
    suspend fun deleteSpellMaterialByIds(spellId: String, itemId: String)
}