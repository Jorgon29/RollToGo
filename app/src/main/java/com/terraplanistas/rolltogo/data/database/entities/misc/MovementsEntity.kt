package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.MovementTypeEnum
import com.terraplanistas.rolltogo.data.enums.RangeUnitEnum

@Entity(
    tableName = "movements",

)
data class MovementsEntity(
    @PrimaryKey val id: String,
    val max_movement_value: Int,
    val max_movement_unit: RangeUnitEnum,
    val movement_type_enum: MovementTypeEnum
)