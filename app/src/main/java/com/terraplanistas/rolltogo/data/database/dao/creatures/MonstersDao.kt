package com.terraplanistas.rolltogo.data.database.dao.creatures

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.creatures.MonstersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MonstersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMonster(monster: MonstersEntity)

    @Update
    suspend fun updateMonster(monster: MonstersEntity)

    @Delete
    suspend fun deleteMonster(monster: MonstersEntity)

    @Query("DELETE FROM monsters WHERE id = :monsterId")
    suspend fun deleteMonsterById(monsterId: String)

    @Query("SELECT * FROM monsters WHERE id = :monsterId")
    fun getMonsterById(monsterId: String): Flow<MonstersEntity?>

    @Query("SELECT * FROM monsters WHERE challenge_rating = :challengeRating")
    fun getMonstersByChallengeRating(challengeRating: Double): Flow<List<MonstersEntity>>

    @Query("SELECT * FROM monsters WHERE legendary = :isLegendary")
    fun getLegendaryMonsters(isLegendary: Boolean): Flow<List<MonstersEntity>>

    @Query("SELECT * FROM monsters WHERE lair IS NOT NULL AND lair != ''")
    fun getMonstersWithLair(): Flow<List<MonstersEntity>>

    @Query("SELECT * FROM monsters")
    fun getAllMonsters(): Flow<List<MonstersEntity>>
}