package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.ProficiencyLevelEnum
import com.terraplanistas.rolltogo.data.enums.SkillTypeEnum

data class SkillResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("abilityId")
    val abilityId: String?,

    @SerializedName("skillTypeEnum")
    val skillTypeEnum: SkillTypeEnum,

    @SerializedName("proficiencyLevelEnum")
    val proficiencyLevelEnum: ProficiencyLevelEnum
)