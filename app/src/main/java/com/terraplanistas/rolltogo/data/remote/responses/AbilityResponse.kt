package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum

data class AbilityResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("content")
    val content: ContentResponse,

    @SerializedName("abilityTypeEnum")
    val abilityTypeEnum: AbilityTypeEnum,

    @SerializedName("modifier")
    val modifier: Int,

    @SerializedName("value")
    val value: Int
)
