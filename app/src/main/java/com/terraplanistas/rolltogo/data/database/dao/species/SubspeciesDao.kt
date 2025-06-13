package com.terraplanistas.rolltogo.data.database.dao.species

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.species.SubspeciesEntity
import com.terraplanistas.rolltogo.data.enums.SizeEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface SubspeciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubspecies(subspecies: SubspeciesEntity)

    @Update
    suspend fun updateSubspecies(subspecies: SubspeciesEntity)

    @Delete
    suspend fun deleteSubspecies(subspecies: SubspeciesEntity)

    @Query("SELECT * FROM subspecies WHERE id = :subspeciesId")
    fun getSubspeciesById(subspeciesId: String): Flow<SubspeciesEntity?>

    @Query("SELECT * FROM subspecies WHERE name LIKE :searchQuery || '%'")
    fun searchSubspeciesByName(searchQuery: String): Flow<List<SubspeciesEntity>>

    @Query("SELECT * FROM subspecies WHERE species_id = :speciesId")
    fun getSubspeciesBySpeciesId(speciesId: String): Flow<List<SubspeciesEntity>>

    @Query("SELECT * FROM subspecies WHERE size_enum = :size")
    fun getSubspeciesBySize(size: SizeEnum): Flow<List<SubspeciesEntity>>

    @Query("SELECT * FROM subspecies")
    fun getAllSubspecies(): Flow<List<SubspeciesEntity>>
}