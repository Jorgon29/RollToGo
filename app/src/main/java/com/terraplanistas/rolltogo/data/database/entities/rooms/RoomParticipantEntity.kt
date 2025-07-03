package com.terraplanistas.rolltogo.data.database.entities.rooms

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import com.terraplanistas.rolltogo.data.database.entities.UserEntity
import com.terraplanistas.rolltogo.data.enums.RoleEnum

@Entity(
    tableName = "room_participants",
    primaryKeys = ["user_id", "room_id"],
    indices = [Index(value = ["user_id", "room_id"], unique = true)],

)
data class RoomParticipantEntity(
    val user_id: String,
    val room_id: String,
    val role_enum: RoleEnum
)