package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName

data class ItemTagResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("tag")
    val tag: String
)