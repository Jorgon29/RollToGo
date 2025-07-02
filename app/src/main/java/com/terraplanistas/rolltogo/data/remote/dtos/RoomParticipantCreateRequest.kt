package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.RoleEnum

data class RoomParticipantCreateRequest(
    @SerializedName("roomId")
    val roomId: String,

    @SerializedName("userId")
    val userId: String,

    @SerializedName("roleEnum")
    val roleEnum: RoleEnum
)
