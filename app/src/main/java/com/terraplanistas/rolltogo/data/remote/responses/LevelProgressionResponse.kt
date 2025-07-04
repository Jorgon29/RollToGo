package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName

data class LevelProgressionResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("level")
    val level: Int,

    @SerializedName("content")
    val content: ContentResponse,

    @SerializedName("newSpecialValue")
    val newSpecialValue: Int?,

    @SerializedName("newSpecialDieFormula")
    val newSpecialDieFormula: String?
)