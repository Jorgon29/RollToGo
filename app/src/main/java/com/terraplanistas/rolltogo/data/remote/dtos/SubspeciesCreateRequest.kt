package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.CreatureSizeEnum

data class SubspeciesCreateRequest(
    @SerializedName("contentId")
    val contentId: String,

    @SerializedName("speciesId")
    val speciesId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("languages")
    val languages: String?,

    @SerializedName("sizeEnum")
    val sizeEnum: CreatureSizeEnum
)