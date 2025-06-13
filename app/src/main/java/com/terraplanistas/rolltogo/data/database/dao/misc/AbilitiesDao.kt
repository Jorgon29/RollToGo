package com.terraplanistas.rolltogo.data.database.dao.misc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.misc.AbilitiesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AbilitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbility(ability: AbilitiesEntity)

    @Update
    suspend fun updateAbility(ability: AbilitiesEntity)

    @Delete
    suspend fun deleteAbility(ability: AbilitiesEntity)

    @Query("SELECT * FROM abilities WHERE id = :abilityId")
    fun getAbilityById(abilityId: String): Flow<AbilitiesEntity?>

    @Query("SELECT * FROM abilities")
    fun getAllAbilities(): Flow<List<AbilitiesEntity>>
}