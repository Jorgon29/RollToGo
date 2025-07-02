package com.terraplanistas.rolltogo.data.remote.dtos
import com.google.gson.annotations.SerializedName
data class BackgroundCreateRequest(
    @SerializedName("contentId")
    val contentId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?
)