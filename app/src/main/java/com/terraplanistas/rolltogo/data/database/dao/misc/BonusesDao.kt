package com.terraplanistas.rolltogo.data.database.dao.misc

import com.terraplanistas.rolltogo.data.enums.BonusTypeEnum
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.misc.BonusesEntity
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.SkillTypeEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface BonusesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBonus(bonus: BonusesEntity)

    @Update
    suspend fun updateBonus(bonus: BonusesEntity)

    @Delete
    suspend fun deleteBonus(bonus: BonusesEntity)

    @Query("SELECT * FROM bonuses WHERE id = :bonusId")
    fun getBonusById(bonusId: String): Flow<BonusesEntity?>

    @Query("SELECT * FROM bonuses WHERE bonus_type_enum = :bonusType")
    fun getBonusesByType(bonusType: BonusTypeEnum): Flow<List<BonusesEntity>>

    @Query("SELECT * FROM bonuses WHERE ability_enum = :ability")
    fun getBonusesByAbility(ability: AbilityTypeEnum): Flow<List<BonusesEntity>>

    @Query("SELECT * FROM bonuses WHERE skill_enum = :skill")
    fun getBonusesBySkill(skill: SkillTypeEnum): Flow<List<BonusesEntity>>

    @Query("SELECT * FROM bonuses")
    fun getAllBonuses(): Flow<List<BonusesEntity>>
}