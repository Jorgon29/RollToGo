package com.terraplanistas.rolltogo.data.database.dao.misc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.misc.LimitedUsagesEntity
import com.terraplanistas.rolltogo.data.enums.RecoveryTypeEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface LimitedUsagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLimitedUsage(limitedUsage: LimitedUsagesEntity)

    @Update
    suspend fun updateLimitedUsage(limitedUsage: LimitedUsagesEntity)

    @Delete
    suspend fun deleteLimitedUsage(limitedUsage: LimitedUsagesEntity)

    @Query("SELECT * FROM limited_usages WHERE id = :usageId")
    fun getLimitedUsageById(usageId: String): Flow<LimitedUsagesEntity?>

    @Query("SELECT * FROM limited_usages WHERE is_scaling = :isScaling")
    fun getLimitedUsagesByScaling(isScaling: Boolean): Flow<List<LimitedUsagesEntity>>

    @Query("SELECT * FROM limited_usages WHERE recovery_type_enum = :recoveryType")
    fun getLimitedUsagesByRecoveryType(recoveryType: RecoveryTypeEnum): Flow<List<LimitedUsagesEntity>>

    @Query("SELECT * FROM limited_usages")
    fun getAllLimitedUsages(): Flow<List<LimitedUsagesEntity>>
}