package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class ItemTagCreateRequest(
    @SerializedName("itemId")
    val itemId: String,

    @SerializedName("tag")
    val tag: String
)