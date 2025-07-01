package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName

data class RoomResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("content")
    val content: ContentResponse,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?,
    @SerializedName("roomParticipants")
    val roomParticipants: List<RoomParticipantResponse>
)
