package com.terraplanistas.rolltogo.data.remote.responses
import com.google.gson.annotations.SerializedName

data class GrantOptionSetResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("contentId")
    val contentId: String,

    @SerializedName("minChoices")
    val minChoices: Int,

    @SerializedName("maxChoices")
    val maxChoices: Int
)