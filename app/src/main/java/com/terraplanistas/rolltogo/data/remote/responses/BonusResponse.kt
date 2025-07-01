package com.terraplanistas.rolltogo.data.remote.responses
import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.BonusTypeEnum
import com.terraplanistas.rolltogo.data.enums.SkillTypeEnum

data class BonusResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("bonusTypeEnum")
    val bonusTypeEnum: BonusTypeEnum,

    @SerializedName("abilityTypeEnum")
    val abilityTypeEnum: AbilityTypeEnum,

    @SerializedName("skillTypeEnum")
    val skillTypeEnum: SkillTypeEnum,

    @SerializedName("diceFormula")
    val diceFormula: String
)
