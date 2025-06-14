package com.terraplanistas.rolltogo.data.database.dao.misc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.misc.AbilityScoreImprovementEntity
import com.terraplanistas.rolltogo.data.enums.SenseTypeEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface AbilityScoreImprovementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbilityScoreImprovement(abilityScoreImprovement: AbilityScoreImprovementEntity)

    @Update
    suspend fun updateAbilityScoreImprovement(abilityScoreImprovement: AbilityScoreImprovementEntity)

    @Delete
    suspend fun deleteAbilityScoreImprovement(abilityScoreImprovement: AbilityScoreImprovementEntity)

    @Query("SELECT * FROM ability_score_improvement WHERE id = :improvementId")
    fun getAbilityScoreImprovementById(improvementId: String): Flow<AbilityScoreImprovementEntity?>

    @Query("SELECT * FROM ability_score_improvement")
    fun getAllAbilityScoreImprovements(): Flow<List<AbilityScoreImprovementEntity>>
}