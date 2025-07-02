package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class MonsterCreateRequest(
    @SerializedName("creatureId")
    val creatureId: String,

    @SerializedName("challengeRating")
    val challengeRating: String,

    @SerializedName("legendary")
    val legendary: Boolean,

    @SerializedName("lair")
    val lair: Boolean
)