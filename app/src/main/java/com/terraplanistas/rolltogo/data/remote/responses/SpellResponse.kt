package com.terraplanistas.rolltogo.data.remote.responses
import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.SpellLevelEnum
import com.terraplanistas.rolltogo.data.enums.SpellSchoolEnum
import com.terraplanistas.rolltogo.data.enums.CastingTimeUnitEnum
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum
import com.terraplanistas.rolltogo.data.enums.RangeUnitEnum


data class SpellResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("content")
    val content: ContentResponse,

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