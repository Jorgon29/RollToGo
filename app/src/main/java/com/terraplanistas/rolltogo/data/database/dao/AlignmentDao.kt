package com.terraplanistas.rolltogo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.terraplanistas.rolltogo.data.database.entities.AlignmentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlignmentDao {
    @Query("SELECT * FROM ALIGNMENT")
    fun getAlignments(): Flow<List<AlignmentEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAlignment(alignmentEntity: AlignmentEntity)
}