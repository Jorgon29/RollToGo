package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class LevelProgressionCreateRequest(
    @SerializedName("contentId")
    val contentId: String,

    @SerializedName("level")
    val level: Int,

    @SerializedName("newSpecialValue")
    val newSpecialValue: Int?,

    @SerializedName("newSpecialDieFormula")
    val newSpecialDieFormula: String?
)
