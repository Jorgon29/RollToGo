package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.SenseTypeEnum

@Entity(
    tableName = "ability_score_improvement",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        )
    ]
)
data class AbilityScoreImprovementEntity(
    @PrimaryKey val id: String,
    val senses_type_enum: SenseTypeEnum,
    val default: String,
    val distance_value: Int,
    val movement_unit_enum: String
)