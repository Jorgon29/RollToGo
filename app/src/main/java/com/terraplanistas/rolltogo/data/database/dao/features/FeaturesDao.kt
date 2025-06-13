package com.terraplanistas.rolltogo.data.database.dao.features

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.features.FeaturesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FeaturesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeature(feature: FeaturesEntity)

    @Update
    suspend fun updateFeature(feature: FeaturesEntity)

    @Delete
    suspend fun deleteFeature(feature: FeaturesEntity)

    @Query("SELECT * FROM features WHERE id = :featureId")
    fun getFeatureById(featureId: String): Flow<FeaturesEntity?>

    @Query("SELECT * FROM features WHERE name LIKE :searchQuery || '%'")
    fun searchFeaturesByName(searchQuery: String): Flow<List<FeaturesEntity>>

    @Query("SELECT * FROM features WHERE source_type_enum = :sourceType")
    fun getFeaturesBySourceType(sourceType: String): Flow<List<FeaturesEntity>>

    @Query("SELECT * FROM features WHERE is_magilcal = :isMagical")
    fun getMagicalFeatures(isMagical: Boolean): Flow<List<FeaturesEntity>>

    @Query("SELECT * FROM features WHERE is_passive = :isPassive")
    fun getPassiveFeatures(isPassive: Boolean): Flow<List<FeaturesEntity>>

    @Query("SELECT * FROM features")
    fun getAllFeatures(): Flow<List<FeaturesEntity>>
}