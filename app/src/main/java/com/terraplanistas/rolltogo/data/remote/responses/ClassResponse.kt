package com.terraplanistas.rolltogo.data.remote.services

import com.google.gson.annotations.SerializedName

data class ClassResponse(
    @SerializedName("id")
    val id: String,

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