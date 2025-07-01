package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.CreatureSizeEnum

data class SubspeciesResponse(
    @SerializedName("id")
    val id: String,

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