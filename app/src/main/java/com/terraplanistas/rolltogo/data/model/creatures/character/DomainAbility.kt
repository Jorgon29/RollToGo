package com.terraplanistas.rolltogo.data.model.creatures.character

import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum

data class DomainAbility(
    val id: String,
    val abilityEnum: AbilityTypeEnum,
    val modifier: Int
)