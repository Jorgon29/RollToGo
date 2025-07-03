package com.terraplanistas.rolltogo.data.database.entities.rooms

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.enums.RoleEnum

@Entity(
    tableName = "room_participants",
)
data class RoomParticipantEntity(
    @PrimaryKey val id: String,
    val user_id: String,
    val room_id: String,
    val role_enum: RoleEnum
)