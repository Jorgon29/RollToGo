package com.terraplanistas.rolltogo.data.database.dao.creatures

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.creatures.InvocationEntity
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface InvocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInvocation(invocation: InvocationEntity)

    @Update
    suspend fun updateInvocation(invocation: InvocationEntity)

    @Delete
    suspend fun deleteInvocation(invocation: InvocationEntity)

    @Query("SELECT * FROM invocations WHERE id = :invocationId")
    fun getInvocationById(invocationId: String): Flow<InvocationEntity?>

    @Query("SELECT * FROM invocations WHERE duration_unit = :durationUnit")
    fun getInvocationsByDurationUnit(durationUnit: DurationUnitEnum): Flow<List<InvocationEntity>>

    @Query("SELECT * FROM invocations")
    fun getAllInvocations(): Flow<List<InvocationEntity>>
}