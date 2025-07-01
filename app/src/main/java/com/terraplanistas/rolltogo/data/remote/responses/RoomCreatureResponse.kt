package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum

data class RoomCreatureResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("creatureId")
    val creatureId: String,

    @SerializedName("ownerId")
    val ownerId: String,

    @SerializedName("roomId")
    val roomId: String,

    @SerializedName("creatureType")
    val creatureType: CreatureTypeEnum
)
