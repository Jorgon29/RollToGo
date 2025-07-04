package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.SpellcastingProgressionEnum
import com.terraplanistas.rolltogo.data.remote.services.ClassResponse

data class SpellcastingResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("clazz")
    val clazz: ClassResponse,

    @SerializedName("spellcastingProgressionEnum")
    val spellcastingProgressionEnum: SpellcastingProgressionEnum,

    @SerializedName("spellcastingAbility")
    val spellcastingAbility: AbilityTypeEnum,

    @SerializedName("preparationFormula")
    val preparationFormula: String?
)