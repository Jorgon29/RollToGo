package com.terraplanistas.rolltogo.data.model.creatures.character

import com.terraplanistas.rolltogo.data.enums.CreatureSizeEnum
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum

data class DomainRace(
    val id: String,
    val name: String,
    val description: String,
    val languages: String,
    val size: CreatureSizeEnum,
    val type: CreatureTypeEnum
)