package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.ConditionTypeEnum
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum

@Entity(
    tableName = "effects",

)
data class EffectsEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val condition_enum: ConditionTypeEnum,
    val duration_value: Int,
    val duration_unit: DurationUnitEnum
)