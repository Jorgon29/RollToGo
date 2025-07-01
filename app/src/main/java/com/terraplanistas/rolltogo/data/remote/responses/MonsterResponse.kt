package com.terraplanistas.rolltogo.data.remote.services

import com.google.gson.annotations.SerializedName

data class MonsterResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("challengeRating")
    val challengeRating: String,

    @SerializedName("legendary")
    val legendary: Boolean,

    @SerializedName("lair")
    val lair: Boolean
)