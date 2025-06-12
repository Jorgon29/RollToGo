package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity

@Entity(
    tableName = "movements",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        )
    ]
)
data class MovementsEntity(
    @PrimaryKey val id: String,
    val max_movement_value: Int,
    val max_movement_unit: String,
    val ability_type_enum: String
)