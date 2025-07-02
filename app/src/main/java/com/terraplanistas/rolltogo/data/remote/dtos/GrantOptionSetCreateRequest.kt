package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class GrantOptionSetCreateRequest(
    @SerializedName("granterContentId")
    val granterContentId: String,

    @SerializedName("minChoices")
    val minChoices: Int,

    @SerializedName("maxChoices")
    val maxChoices: Int
)