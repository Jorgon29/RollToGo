package com.terraplanistas.rolltogo.data.model

import com.terraplanistas.rolltogo.data.database.entities.CampaignEntity

data class Campaign(
    val id: Int,
    val name: String,
    val master: Int,
    val image: String
)

fun Campaign.toEntity(): CampaignEntity{
    return CampaignEntity(
        id = id,
        name = name,
        master = master,
        image = image
    )
}
