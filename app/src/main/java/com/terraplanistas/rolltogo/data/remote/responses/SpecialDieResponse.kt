package com.terraplanistas.rolltogo.data.remote.responses
import com.google.gson.annotations.SerializedName

data class SpecialDieResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("featureId")
    val featureId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("quantity")
    val quantity: Int,

    @SerializedName("dieFormula")
    val dieFormula: String
)