package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName

data class FeatureResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("isMagic")
    val isMagic: Boolean,

    @SerializedName("isPassive")
    val isPassive: Boolean
)