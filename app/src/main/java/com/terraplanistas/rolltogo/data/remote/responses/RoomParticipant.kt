package com.terraplanistas.rolltogo.data.remote.responses
import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.database.entities.rooms.RoomParticipantEntity
import com.terraplanistas.rolltogo.data.enums.RoleEnum

data class RoomParticipantResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("roomId")
    val roomId: String,

    @SerializedName("user")
    val user: UserResponse,

    @SerializedName("roleEnum")
    val roleEnum: RoleEnum
)

fun RoomParticipantResponse.toEntity(): RoomParticipantEntity {
    return RoomParticipantEntity(
        id = id,
        user_id = user.id,
        room_id = roomId,
        role_enum = roleEnum
    )
}