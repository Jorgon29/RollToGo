package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.ProficiencyLevelEnum
import com.terraplanistas.rolltogo.data.enums.SkillTypeEnum
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainSkill

@Entity(
    tableName = "skills",

)
data class SkillEntity(
    @PrimaryKey val id: String,
    val skill_enum: SkillTypeEnum,
    val die_formula: String = "",
    val proficiency_level_enum: ProficiencyLevelEnum,
    val ability: String
)


fun SkillEntity.toDomainSkill(): DomainSkill {
    return DomainSkill(
        id = this.id,
        dieFormula = this.die_formula,
        skill = this.skill_enum,
        proficiency = proficiency_level_enum,
        ability
    )
}