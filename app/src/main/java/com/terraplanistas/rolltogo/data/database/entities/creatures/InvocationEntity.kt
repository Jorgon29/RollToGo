package com.terraplanistas.rolltogo.data.database.entities.creatures

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum
import com.terraplanistas.rolltogo.helpers.typeConverter.EnumConverters

@Entity(
    tableName = "invocations",

)
data class InvocationEntity(
    @PrimaryKey val id: String,
    val duration_unit: DurationUnitEnum,
    val duration_value: Int
)