package com.terraplanistas.rolltogo.data.database.entities.rooms

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.model.room.RoomDomain

@Entity(
    tableName = "rooms",

)
data class RoomsEntity(
    @PrimaryKey val id: String,
    val name: String,
    val ownerUserName: String,
    val description: String
)

fun RoomsEntity.toDomain(): RoomDomain {
    return RoomDomain(
        id = id,
        name = name,
        ownerUserName = ownerUserName,
        description = description
    )

}