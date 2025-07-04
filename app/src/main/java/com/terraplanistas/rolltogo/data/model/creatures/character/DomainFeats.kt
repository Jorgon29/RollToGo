package com.terraplanistas.rolltogo.data.model.creatures.character

import com.terraplanistas.rolltogo.data.database.entities.misc.FeatsEntity

data class DomainFeats(
    val id: String,
    val description: String,
    val name: String,
    val grantId: String
)

fun DomainFeats.toFeatEntity(): FeatsEntity{
    return FeatsEntity(
        id = id,
        name = name,
        description = description
    )
}
