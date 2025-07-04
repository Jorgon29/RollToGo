package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum

data class AbilityScoreImprovementCreateRequest(
    @SerializedName("contentId")
    val contentId: String,

    @SerializedName("abilityTypeEnum")
    val abilityTypeEnum: AbilityTypeEnum,

    @SerializedName("maxPoints")
    val maxPoints: Int
)