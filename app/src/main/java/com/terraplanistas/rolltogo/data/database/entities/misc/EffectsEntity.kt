package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum

@Entity(
    tableName = "effects",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        )
    ]
)
data class EffectsEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val condition_enum: String,
    val duration_value: String,
    val duration_unit: DurationUnitEnum
)