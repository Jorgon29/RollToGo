package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.AbilityEnum
import com.terraplanistas.rolltogo.data.enums.BonusTypeEnum
import com.terraplanistas.rolltogo.data.enums.SkillEnum

@Entity(
    tableName = "bonuses",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        )
    ]
)
data class BonusesEntity(
    @PrimaryKey val id: String,
    val bonus_type_enum: BonusTypeEnum,
    val ability_enum: AbilityEnum,
    val skill_enum: SkillEnum,
    val die_formula: String
)