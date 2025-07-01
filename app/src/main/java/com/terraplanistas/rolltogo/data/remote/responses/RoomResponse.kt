package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName

data class RoomResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?
)
