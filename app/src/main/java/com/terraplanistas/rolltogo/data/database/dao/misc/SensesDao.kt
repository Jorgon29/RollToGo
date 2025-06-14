package com.terraplanistas.rolltogo.data.database.dao.misc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.misc.SensesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SensesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSense(sense: SensesEntity)

    @Update
    suspend fun updateSense(sense: SensesEntity)

    @Delete
    suspend fun deleteSense(sense: SensesEntity)

    @Query("SELECT * FROM senses WHERE id = :senseId")
    fun getSenseById(senseId: String): Flow<SensesEntity?>

    @Query("SELECT * FROM senses WHERE senses_type_enum = :senseType")
    fun getSensesByType(senseType: String): Flow<List<SensesEntity>>

    @Query("SELECT * FROM senses")
    fun getAllSenses(): Flow<List<SensesEntity>>
}