package com.terraplanistas.rolltogo.data.database.dao.misc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.misc.DamagesEntity
import com.terraplanistas.rolltogo.data.enums.DamageTypeEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface DamagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDamage(damage: DamagesEntity)

    @Update
    suspend fun updateDamage(damage: DamagesEntity)

    @Delete
    suspend fun deleteDamage(damage: DamagesEntity)

    @Query("SELECT * FROM damages WHERE id = :damageId")
    fun getDamageById(damageId: String): Flow<DamagesEntity?>

    @Query("SELECT * FROM damages WHERE damage_type_enum = :damageType")
    fun getDamagesByType(damageType: DamageTypeEnum): Flow<List<DamagesEntity>>

    @Query("SELECT * FROM damages WHERE repeat = :repeat")
    fun getDamagesByRepeat(repeat: Boolean): Flow<List<DamagesEntity>>

    @Query("SELECT * FROM damages")
    fun getAllDamages(): Flow<List<DamagesEntity>>
}