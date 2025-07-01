package com.terraplanistas.rolltogo.data.remote.services

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.remote.responses.CreatureResponse

data class MonsterResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("creature")
    val creature: CreatureResponse,

    @SerializedName("challengeRating")
    val challengeRating: String,

    @SerializedName("legendary")
    val legendary: Boolean,

    @SerializedName("lair")
    val lair: Boolean
)