package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.DamageTypeEnum

@Entity(
    tableName = "damages",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class DamagesEntity(
    @PrimaryKey val id: String,
    val damage_formula: String,
    val damage_type_enum: DamageTypeEnum,
    val repeat: Boolean,
    val repetition_value: String,
    val repetition_unit: String
)