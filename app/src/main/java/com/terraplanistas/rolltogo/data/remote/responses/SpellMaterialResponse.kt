package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName

data class SpellMaterialResponse(
    @SerializedName("spellId")
    val spellId: String,

    @SerializedName("itemId")
    val itemId: String
)
