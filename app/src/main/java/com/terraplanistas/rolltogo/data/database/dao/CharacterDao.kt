package com.terraplanistas.rolltogo.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.terraplanistas.rolltogo.data.database.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow


    @Dao
    interface CharacterDao {
        @Query("SELECT * FROM characters")
        fun getAll(): Flow<List<CharacterEntity>>

        @Query("SELECT * FROM characters WHERE id = :id")
        fun getById(id: Int): Flow<CharacterEntity>?

        @Query("SELECT * FROM characters WHERE name LIKE '%' || :name || '%' COLLATE NOCASE")
        fun searchByName(name: String): Flow<List<CharacterEntity>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(character: CharacterEntity): Long

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAll(characters: List<CharacterEntity>)

        @Delete
        suspend fun delete(character: CharacterEntity)

        @Query("DELETE FROM characters WHERE id = :id")
        suspend fun deleteById(id: Int)

        @Update
        suspend fun update(character: CharacterEntity)

        @Query("SELECT * FROM characters WHERE race = :race")
        fun getByRace(race: String): Flow<List<CharacterEntity>>

        @Query("SELECT * FROM characters WHERE characterClass = :characterClass")
        fun getByClass(characterClass: String): Flow<List<CharacterEntity>>

        @Query("SELECT * FROM characters WHERE level BETWEEN :minLevel AND :maxLevel")
        fun getByLevelRange(minLevel: Int, maxLevel: Int): Flow<List<CharacterEntity>>

        @Query("SELECT COUNT(*) FROM characters")
        suspend fun getCount(): Int
    }
