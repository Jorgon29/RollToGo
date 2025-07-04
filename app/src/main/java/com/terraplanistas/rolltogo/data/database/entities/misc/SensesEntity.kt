package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.RangeUnitEnum
import com.terraplanistas.rolltogo.data.enums.SensesTypeEnum

@Entity(
    tableName = "senses",

)
data class SensesEntity(
    @PrimaryKey val id: String,
    val senses_type_enum: SensesTypeEnum,
    val range_value: Int,
    val range_unit_enum: RangeUnitEnum
)