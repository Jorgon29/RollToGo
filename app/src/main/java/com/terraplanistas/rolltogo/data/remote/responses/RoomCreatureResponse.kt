package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum

data class RoomCreatureResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("creature")
    val creature: CreatureResponse,

    @SerializedName("owner")
    val owner: UserResponse,

    @SerializedName("room")
    val room: RoomResponse,

    @SerializedName("creatureType")
    val creatureType: CreatureTypeEnum
)
