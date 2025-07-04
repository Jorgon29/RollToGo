package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.CastingTimeUnitEnum
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum
import com.terraplanistas.rolltogo.data.enums.RangeUnitEnum
import com.terraplanistas.rolltogo.data.enums.SpellLevelEnum
import com.terraplanistas.rolltogo.data.enums.SpellSchoolEnum

data class SpellCreateRequest(
    @SerializedName("contentId")
    val contentId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("spellComponents")
    val spellComponents: String,

    @SerializedName("spellLevelEnum")
    val spellLevelEnum: SpellLevelEnum,

    @SerializedName("spellSchoolEnum")
    val spellSchoolEnum: SpellSchoolEnum,

    @SerializedName("castingTimeValue")
    val castingTimeValue: Int,

    @SerializedName("castingTimeUnitEnum")
    val castingTimeUnitEnum: CastingTimeUnitEnum,

    @SerializedName("rangeValue")
    val rangeValue: Int,

    @SerializedName("rangeUnitEnum")
    val rangeUnitEnum: RangeUnitEnum,

    @SerializedName("durationValue")
    val durationValue: Int,

    @SerializedName("durationUnitEnum")
    val durationUnitEnum: DurationUnitEnum,

    @SerializedName("isRitual")
    val isRitual: Boolean
)