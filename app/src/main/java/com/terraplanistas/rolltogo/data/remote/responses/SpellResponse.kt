package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.SpellLevelEnum
import com.terraplanistas.rolltogo.data.enums.SpellSchoolEnum
import com.terraplanistas.rolltogo.data.enums.CastingTimeUnitEnum
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum
import com.terraplanistas.rolltogo.data.enums.RangeUnitEnum
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainSpell


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

fun SpellResponse.toDomain(grandId: String): DomainSpell{
    return DomainSpell(
        id = id,
        name = name,
        description = description?: "",
        spellMaterials = emptyList(),
        components = spellComponents,
        spellSchoolEnum = spellSchoolEnum,
        castingTime = buildString {
            append(castingTimeValue)
            append(" ")
            append(castingTimeUnitEnum)
        },
        range = buildString {
            append(castingTimeValue)
            append(" ")
            append(castingTimeUnitEnum)
        },
        duration = buildString {
            append(durationValue)
            append(" ")
            append(durationUnitEnum)
        },
        level = spellLevelEnum,
        isRitual = isRitual,
        grantId = grandId
    )
}