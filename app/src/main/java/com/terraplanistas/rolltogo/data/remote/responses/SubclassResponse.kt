package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.remote.services.ClassResponse

data class SubclassResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("classId")
    val classId: String,

    @SerializedName("clazz")
    val clazz: ClassResponse,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?
)