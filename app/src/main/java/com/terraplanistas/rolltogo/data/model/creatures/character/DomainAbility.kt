package com.terraplanistas.rolltogo.data.model.creatures.character

import com.terraplanistas.rolltogo.data.enums.AbilityEnum

data class DomainAbility(
    val id: String,
    val abilityEnum: AbilityEnum,
    val modifier: Int
)