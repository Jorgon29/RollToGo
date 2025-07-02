package com.terraplanistas.rolltogo.data.remote.dtos


import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.ProficiencyTypeEnum

data class ProficiencyCreateRequest(
    @SerializedName("contentId")
    val contentId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("proficiencyTypeEnum")
    val proficiencyTypeEnum: ProficiencyTypeEnum
)