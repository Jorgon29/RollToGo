package com.terraplanistas.rolltogo.data.database.dao.creatures

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.creatures.CharactersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharactersEntity)

    @Update
    suspend fun updateCharacter(character: CharactersEntity)

    @Delete
    suspend fun deleteCharacter(character: CharactersEntity)

    @Query("SELECT * FROM characters WHERE id = :characterId")
    fun getCharacterById(characterId: String): Flow<CharactersEntity?>

    @Query("SELECT * FROM characters WHERE race_id = :raceId")
    fun getCharactersByRaceId(raceId: String): Flow<List<CharactersEntity>>

    @Query("SELECT * FROM characters WHERE total_level = :level")
    fun getCharactersByLevel(level: Int): Flow<List<CharactersEntity>>

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<CharactersEntity>>
}