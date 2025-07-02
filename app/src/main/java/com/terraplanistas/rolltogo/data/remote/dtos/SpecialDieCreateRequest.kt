package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class SpecialDieCreateRequest(
    @SerializedName("featureId")
    val featureId: UUID,
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("dieFormula")
    val dieFormula: String
)