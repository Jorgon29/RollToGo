package com.terraplanistas.rolltogo.data.database.dao.misc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.misc.ProficienciesEntity
import com.terraplanistas.rolltogo.data.enums.ProficiencyTypeEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface ProficienciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProficiency(proficiency: ProficienciesEntity)

    @Update
    suspend fun updateProficiency(proficiency: ProficienciesEntity)

    @Delete
    suspend fun deleteProficiency(proficiency: ProficienciesEntity)

    @Query("SELECT * FROM proficiencies WHERE id = :proficiencyId")
    fun getProficiencyById(proficiencyId: String): Flow<ProficienciesEntity?>

    @Query("SELECT * FROM proficiencies WHERE name LIKE :searchQuery || '%'")
    fun searchProficienciesByName(searchQuery: String): Flow<List<ProficienciesEntity>>

    @Query("SELECT * FROM proficiencies WHERE proficiency_type_enum = :proficiencyType")
    fun getProficienciesByType(proficiencyType: ProficiencyTypeEnum): Flow<List<ProficienciesEntity>>

    @Query("SELECT * FROM proficiencies")
    fun getAllProficiencies(): Flow<List<ProficienciesEntity>>
}