package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.ProficiencyTypeEnum

data class ProficiencyResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("content")
    val content: ContentResponse,

    @SerializedName("name")
    val name: String,

    @SerializedName("proficiencyTypeEnum")
    val proficiencyTypeEnum: ProficiencyTypeEnum
)