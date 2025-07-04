package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.CreatureSizeEnum
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum

data class SpeciesCreateRequest(
    @SerializedName("contentId")
    val contentId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("creatureTypeEnum")
    val creatureTypeEnum: CreatureTypeEnum,

    @SerializedName("languages")
    val languages: String?,

    @SerializedName("sizeEnum")
    val sizeEnum: CreatureSizeEnum
)