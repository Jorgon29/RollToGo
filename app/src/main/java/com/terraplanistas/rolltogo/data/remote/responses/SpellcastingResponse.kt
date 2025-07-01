package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.SpellcastingProgressionEnum

data class SpellcastingResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("classId")
    val classId: String,

    @SerializedName("spellcastingProgressionEnum")
    val spellcastingProgressionEnum: SpellcastingProgressionEnum,

    @SerializedName("spellcastingAbility")
    val spellcastingAbility: AbilityTypeEnum,

    @SerializedName("preparationFormula")
    val preparationFormula: String?
)