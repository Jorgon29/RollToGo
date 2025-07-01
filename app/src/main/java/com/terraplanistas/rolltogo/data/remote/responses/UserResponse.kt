package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime

data class UserResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("userImageUrl")
    val userImageUrl: String?,

    @SerializedName("username")
    val username: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("createdAt")
    val createdAt: OffsetDateTime?,
    @SerializedName("roomParticipants")
    val roomParticipants: List<RoomParticipantResponse>
)