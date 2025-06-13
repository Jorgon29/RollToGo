package com.terraplanistas.rolltogo.data.database.dao.misc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.misc.FeatsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FeatsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeat(feat: FeatsEntity)

    @Update
    suspend fun updateFeat(feat: FeatsEntity)

    @Delete
    suspend fun deleteFeat(feat: FeatsEntity)

    @Query("SELECT * FROM feats WHERE id = :featId")
    fun getFeatById(featId: String): Flow<FeatsEntity?>

    @Query("SELECT * FROM feats WHERE name LIKE :searchQuery || '%'")
    fun searchFeatsByName(searchQuery: String): Flow<List<FeatsEntity>>

    @Query("SELECT * FROM feats")
    fun getAllFeats(): Flow<List<FeatsEntity>>
}