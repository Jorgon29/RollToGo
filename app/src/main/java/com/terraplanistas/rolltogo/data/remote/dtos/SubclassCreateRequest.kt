package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class SubclassCreateRequest(
    @SerializedName("contentId")
    val contentId: String,

    @SerializedName("classId")
    val classId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?
)
