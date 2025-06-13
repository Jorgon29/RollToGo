package com.terraplanistas.rolltogo.data.model.character

import com.terraplanistas.rolltogo.data.enums.SpellSchoolEnum

data class DomainSpell(
    val id: String,
    val name: String,
    val description: String,
    val spellComponents: List<DomainItem>,
    val spellSchoolEnum: SpellSchoolEnum,
    val castingTime: String,
    val range: String,
    val duration: String
)
