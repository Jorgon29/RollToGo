package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.DamageTypeEnum
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum

@Entity(
    tableName = "damages",

)
data class DamagesEntity(
    @PrimaryKey val id: String,
    val damage_formula: String,
    val damage_type_enum: DamageTypeEnum,
    val repeat: Boolean,
    val repetition_value: Int,
    val repetition_unit: DurationUnitEnum
)