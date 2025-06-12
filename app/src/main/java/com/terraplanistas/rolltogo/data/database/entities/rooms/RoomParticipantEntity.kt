package com.terraplanistas.rolltogo.data.database.entities.rooms

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import com.terraplanistas.rolltogo.data.database.entities.UserEntity
import com.terraplanistas.rolltogo.data.enums.GameRoleEnum

@Entity(
    tableName = "room_participants",
    primaryKeys = ["id", "room_id"],
    indices = [Index(value = ["id"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = RoomsEntity::class,
            parentColumns = ["id"],
            childColumns = ["room_id"],
            onDelete = CASCADE
        )
    ]
)
data class RoomParticipantEntity(
    val id: String,
    val room_id: String,
    val role_enum: GameRoleEnum
)