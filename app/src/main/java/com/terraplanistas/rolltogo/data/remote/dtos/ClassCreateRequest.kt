package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class ClassCreateRequest(
    @SerializedName("contentId")
    val contentId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("hitDice")
    val hitDice: String,

    @SerializedName("hitPointsFirstLevel")
    val hitPointsFirstLevel: Int,

    @SerializedName("hitPointsPerLevel")
    val hitPointsPerLevel: String
)