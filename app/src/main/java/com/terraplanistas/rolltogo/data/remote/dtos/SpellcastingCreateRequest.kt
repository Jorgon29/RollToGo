package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.SpellcastingProgressionEnum
import java.util.UUID

data class SpellcastingCreateRequest(
    @SerializedName("classId")
    val classId: UUID,

    @SerializedName("spellcastingProgressionEnum")
    val spellcastingProgressionEnum: SpellcastingProgressionEnum,

    @SerializedName("spellcastingAbility")
    val spellcastingAbility: AbilityTypeEnum,

    @SerializedName("preparationFormula")
    val preparationFormula: String?
)