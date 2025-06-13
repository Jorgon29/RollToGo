package com.terraplanistas.rolltogo.data.model.character

import com.terraplanistas.rolltogo.data.enums.SkillEnum

data class DomainSkill(
    val id: String,
    val dieFormula: String,
    val skill: SkillEnum
)
