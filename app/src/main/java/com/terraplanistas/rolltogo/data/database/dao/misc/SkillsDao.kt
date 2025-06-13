package com.terraplanistas.rolltogo.data.database.dao.misc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.misc.SkillEntity
import com.terraplanistas.rolltogo.data.enums.SkillEnum
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
    fun getSkillById(skillId: Int): Flow<SkillEntity?>

    @Query("SELECT * FROM skills WHERE skill_enum = :skillEnum")
    fun getSkillByEnum(skillEnum: SkillEnum): Flow<SkillEntity?>

    @Query("SELECT * FROM skills WHERE proficiency_level_enum = :proficiencyLevel")
    fun getSkillsByProficiencyLevel(proficiencyLevel: String): Flow<List<SkillEntity>>

    @Query("SELECT * FROM skills")
    fun getAllSkills(): Flow<List<SkillEntity>>
}