package com.terraplanistas.rolltogo.data.remote.responses

data class RoomParticipantResponse(
    @SerializedName("roomId")
    val roomId: String,

    @SerializedName("userId")
    val userId: String,

    @SerializedName("roleEnum")
    val roleEnum: RoleEnum
)