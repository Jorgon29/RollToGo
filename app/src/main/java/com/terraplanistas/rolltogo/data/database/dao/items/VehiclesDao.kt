package com.terraplanistas.rolltogo.data.database.dao.items

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.items.VehiclesEntity
import com.terraplanistas.rolltogo.data.enums.CoverTypeEnum
import com.terraplanistas.rolltogo.data.enums.SkillEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface VehiclesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicle(vehicle: VehiclesEntity)

    @Update
    suspend fun updateVehicle(vehicle: VehiclesEntity)

    @Delete
    suspend fun deleteVehicle(vehicle: VehiclesEntity)

    @Query("SELECT * FROM vehicles WHERE id = :vehicleId")
    fun getVehicleById(vehicleId: String): Flow<VehiclesEntity?>

    @Query("SELECT * FROM vehicles WHERE item_id = :itemId")
    fun getVehicleByItemId(itemId: String): Flow<VehiclesEntity?>

    @Query("SELECT * FROM vehicles WHERE cover_type_enum = :coverType")
    fun getVehiclesByCoverType(coverType: CoverTypeEnum): Flow<List<VehiclesEntity>>

    @Query("SELECT * FROM vehicles WHERE skill_enum = :skill")
    fun getVehiclesBySkill(skill: SkillEnum): Flow<List<VehiclesEntity>>

    @Query("SELECT * FROM vehicles")
    fun getAllVehicles(): Flow<List<VehiclesEntity>>
}