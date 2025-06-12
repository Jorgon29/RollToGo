package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.SkillEnum

@Entity(
    tableName = "skills",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        )
    ]
)
data class SkillEntity(
    @PrimaryKey val id: Int,
    val skill_enum: SkillEnum,
    val die_formula: String,
    val proficiency_level_enum: String
)