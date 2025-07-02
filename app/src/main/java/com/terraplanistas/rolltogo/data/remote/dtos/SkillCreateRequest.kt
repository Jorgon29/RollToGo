package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.ProficiencyLevelEnum
import com.terraplanistas.rolltogo.data.enums.SkillTypeEnum

data class SkillCreateRequest(
    @SerializedName("contentId")
    val contentId: String,
    @SerializedName("skillTypeEnum")
    val skillTypeEnum: SkillTypeEnum,
    @SerializedName("proficiencyLevelEnum")
    val proficiencyLevelEnum: ProficiencyLevelEnum,
    @SerializedName("abilityId")
    val abilityId: String
)