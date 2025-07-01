package com.terraplanistas.rolltogo.data.remote.responses
import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.RoleEnum

data class RoomParticipantResponse(
    @SerializedName("roomId")
    val roomId: String,

    @SerializedName("userId")
    val userId: String,

    @SerializedName("roleEnum")
    val roleEnum: RoleEnum
)