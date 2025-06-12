package com.terraplanistas.rolltogo.data.database.entities.items

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.enums.CoverTypeEnum
import com.terraplanistas.rolltogo.data.enums.SkillEnum

@Entity(
    tableName = "vehicles",
    foreignKeys = [
        ForeignKey(
            entity = ItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["item_id"],
            onDelete = CASCADE
        )
    ]
)
data class VehiclesEntity(
    @PrimaryKey val id: String,
    val item_id: String,
    val armor_class: Int,
    val cover_type_enum: CoverTypeEnum,
    val hit_points: Int,
    val skill_enum: SkillEnum,
    val die_formula: String
)