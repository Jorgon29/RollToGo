package com.terraplanistas.rolltogo.data.database.dao.creatures

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.creatures.CreaturesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CreaturesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCreature(creature: CreaturesEntity)

    @Update
    suspend fun updateCreature(creature: CreaturesEntity)

    @Delete
    suspend fun deleteCreature(creature: CreaturesEntity)

    @Query("SELECT * FROM creatures WHERE id = :creatureId")
    fun getCreatureById(creatureId: String): Flow<CreaturesEntity?>

    @Query("SELECT * FROM creatures WHERE name LIKE :searchQuery || '%'")
    fun searchCreaturesByName(searchQuery: String): Flow<List<CreaturesEntity>>

    @Query("SELECT * FROM creatures WHERE size_enum = :size")
    fun getCreaturesBySize(size: String): Flow<List<CreaturesEntity>>

    @Query("SELECT * FROM creatures WHERE type_enum = :type")
    fun getCreaturesByType(type: String): Flow<List<CreaturesEntity>>

    @Query("SELECT * FROM creatures WHERE alignment_enum = :alignment")
    fun getCreaturesByAlignment(alignment: String): Flow<List<CreaturesEntity>>

    @Query("SELECT * FROM creatures WHERE creature_source_type = :sourceType")
    fun getCreaturesBySourceType(sourceType: String): Flow<List<CreaturesEntity>>

    @Query("SELECT * FROM creatures")
    fun getAllCreatures(): Flow<List<CreaturesEntity>>
}