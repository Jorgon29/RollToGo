package com.terraplanistas.rolltogo.data.model.character

import com.terraplanistas.rolltogo.data.enums.SizeEnum

data class DomainRace(
    val id: String,
    val name: String,
    val description: String,
    val languages: String,
    val size: SizeEnum
)