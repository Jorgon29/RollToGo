package com.terraplanistas.rolltogo.data.database.dao.species

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.species.SpeciesEntity
import com.terraplanistas.rolltogo.data.enums.CreatureSizeEnum
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface SpeciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecies(species: SpeciesEntity)

    @Update
    suspend fun updateSpecies(species: SpeciesEntity)

    @Delete
    suspend fun deleteSpecies(species: SpeciesEntity)

    @Query("SELECT * FROM species WHERE id = :speciesId")
    fun getSpeciesById(speciesId: String): Flow<SpeciesEntity?>

    @Query("SELECT * FROM species WHERE name LIKE :searchQuery || '%'")
    fun searchSpeciesByName(searchQuery: String): Flow<List<SpeciesEntity>>

    @Query("SELECT * FROM species WHERE creature_type_enum = :creatureType")
    fun getSpeciesByCreatureType(creatureType: CreatureTypeEnum): Flow<List<SpeciesEntity>>

    @Query("SELECT * FROM species WHERE size_enum = :size")
    fun getSpeciesBySize(size: CreatureSizeEnum): Flow<List<SpeciesEntity>>

    @Query("SELECT * FROM species")
    fun getAllSpecies(): Flow<List<SpeciesEntity>>
}