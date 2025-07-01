package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime

data class ChatMessageResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("roomId")
    val roomId: String,

    @SerializedName("sender")
    val sender: String?,

    @SerializedName("content")
    val content: String,

    @SerializedName("createdAt")
    val createdAt: OffsetDateTime
)