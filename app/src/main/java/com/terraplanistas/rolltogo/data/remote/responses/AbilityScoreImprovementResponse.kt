package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum

data class AbilityScoreImprovementResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("abilityTypeEnum")
    val abilityTypeEnum: AbilityTypeEnum,

    @SerializedName("maxPoints")
    val maxPoints: Int
)