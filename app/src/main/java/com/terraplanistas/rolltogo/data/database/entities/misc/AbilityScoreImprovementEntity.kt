package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum

@Entity(
    tableName = "ability_score_improvement",

)
data class AbilityScoreImprovementEntity(
    @PrimaryKey val id: String,
    val ability_type_enum: AbilityTypeEnum,
    val max_points: Int
)