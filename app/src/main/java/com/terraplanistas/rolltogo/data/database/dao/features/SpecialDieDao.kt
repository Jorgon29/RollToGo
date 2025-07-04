package com.terraplanistas.rolltogo.data.database.dao.features

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.features.SpecialDieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpecialDieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecialDie(specialDie: SpecialDieEntity)

    @Update
    suspend fun updateSpecialDie(specialDie: SpecialDieEntity)

    @Delete
    suspend fun deleteSpecialDie(specialDie: SpecialDieEntity)

    @Query("SELECT * FROM special_die WHERE id = :specialDieId")
    fun getSpecialDieById(specialDieId: String): Flow<SpecialDieEntity?>

    @Query("SELECT * FROM special_die WHERE feature_id = :featureId")
    fun getSpecialDiesByFeatureId(featureId: String): Flow<List<SpecialDieEntity>>

    @Query("SELECT * FROM special_die WHERE name LIKE :searchQuery || '%'")
    fun searchSpecialDiesByName(searchQuery: String): Flow<List<SpecialDieEntity>>

    @Query("SELECT * FROM special_die")
    fun getAllSpecialDies(): Flow<List<SpecialDieEntity>>
}