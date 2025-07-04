package com.terraplanistas.rolltogo.data.model.creatures.character

import com.terraplanistas.rolltogo.data.database.entities.misc.SkillEntity
import com.terraplanistas.rolltogo.data.enums.ProficiencyLevelEnum
import com.terraplanistas.rolltogo.data.enums.SkillTypeEnum

data class DomainSkill(
    val id: String,
    val dieFormula: String,
    val skill: SkillTypeEnum,
    val proficiency: ProficiencyLevelEnum,
    val abilityId: String
)

fun DomainSkill.toSkillEntity(): SkillEntity{
    return SkillEntity(
        id = id,
        skill_enum = skill,
        die_formula = dieFormula,
        proficiency_level_enum = proficiency,
        abilityId
    )
}
