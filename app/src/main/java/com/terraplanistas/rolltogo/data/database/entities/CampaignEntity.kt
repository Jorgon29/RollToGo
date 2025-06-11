package com.terraplanistas.rolltogo.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.model.Campaign

@Entity(tableName = "campaign")
data class CampaignEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val master: Int,
    val image: String
)

fun CampaignEntity.toDomain(): Campaign{
    return Campaign(
        id, name, master, image
    )
}
