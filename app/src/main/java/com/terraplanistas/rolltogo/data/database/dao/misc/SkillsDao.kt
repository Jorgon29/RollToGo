package com.terraplanistas.rolltogo.data.database.dao.misc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.misc.SkillEntity
import com.terraplanistas.rolltogo.data.enums.SkillTypeEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface SkillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSkill(skill: SkillEntity)

    @Update
    suspend fun updateSkill(skill: SkillEntity)

    @Delete
    suspend fun deleteSkill(skill: SkillEntity)

    @Query("SELECT * FROM skills WHERE id = :skillId")
    fun getSkillById(skillId: String): Flow<SkillEntity?>

    @Query("SELECT * FROM skills WHERE skill_enum = :skillEnum")
    fun getSkillByEnum(skillEnum: SkillTypeEnum): Flow<SkillEntity?>

    @Query("SELECT * FROM skills WHERE proficiency_level_enum = :proficiencyLevel")
    fun getSkillsByProficiencyLevel(proficiencyLevel: String): Flow<List<SkillEntity>>

    @Query("SELECT * FROM skills WHERE ability = :ability")
    fun getSkillsByAbilityId(ability: String): Flow<List<SkillEntity>>

    @Query("SELECT * FROM skills")
    fun getAllSkills(): Flow<List<SkillEntity>>
}