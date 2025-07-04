package com.terraplanistas.rolltogo.data.database.dao.misc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.misc.MovementsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovementsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovement(movement: MovementsEntity)

    @Update
    suspend fun updateMovement(movement: MovementsEntity)

    @Delete
    suspend fun deleteMovement(movement: MovementsEntity)

    @Query("SELECT * FROM movements WHERE id = :movementId")
    fun getMovementById(movementId: String): Flow<MovementsEntity?>

    @Query("SELECT * FROM movements WHERE max_movement_unit = :unit")
    fun getMovementsByUnit(unit: String): Flow<List<MovementsEntity>>

    @Query("SELECT * FROM movements")
    fun getAllMovements(): Flow<List<MovementsEntity>>
}