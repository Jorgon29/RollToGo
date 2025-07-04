package com.terraplanistas.rolltogo.data.database.entities.rooms

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.enums.CreatureSourceType
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum

@Entity(
    tableName = "room_creatures",

)
data class RoomCreaturesEntity(
    @PrimaryKey val id: String,
    val creature_id: String,
    val owner_id: String,
    val room_id: String,
    val creature_type: CreatureSourceType
)