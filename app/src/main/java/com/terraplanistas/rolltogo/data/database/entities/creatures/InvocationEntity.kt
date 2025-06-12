package com.terraplanistas.rolltogo.data.database.entities.creatures

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum

@Entity(
    tableName = "invocations",
    foreignKeys = [
        ForeignKey(
            entity = CreaturesEntity::class,
            childColumns = ["id"],
            parentColumns = ["id"]
        )
    ]
)
data class InvocationEntity(
    @PrimaryKey val id: String,
    val duration_unit: DurationUnitEnum,
    val duration_value: Int
)