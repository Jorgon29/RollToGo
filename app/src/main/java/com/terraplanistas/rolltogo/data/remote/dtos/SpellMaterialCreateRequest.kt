package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class SpellMaterialCreateRequest(
    @SerializedName("spellId")
    val spellId: String,

    @SerializedName("itemId")
    val itemId: String
)