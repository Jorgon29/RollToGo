package com.terraplanistas.rolltogo.data.model.creatures.character

import com.terraplanistas.rolltogo.data.database.entities.misc.AbilitiesEntity
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum

data class DomainAbility(
    val id: String,
    val abilityEnum: AbilityTypeEnum,
    val modifier: Int,
    val grantId: String,
    val value: Int
)

fun DomainAbility.toAbilityEntity(): AbilitiesEntity{
    return AbilitiesEntity(
        id = id,
        value = value,
        modifier = modifier,
        ability_enum = abilityEnum
    )
}