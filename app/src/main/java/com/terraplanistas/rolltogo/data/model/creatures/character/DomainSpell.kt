package com.terraplanistas.rolltogo.data.model.creatures.character

import com.terraplanistas.rolltogo.data.database.entities.spells.SpellEntity
import com.terraplanistas.rolltogo.data.enums.CastingTimeUnitEnum
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum
import com.terraplanistas.rolltogo.data.enums.RangeUnitEnum
import com.terraplanistas.rolltogo.data.enums.SpellLevelEnum
import com.terraplanistas.rolltogo.data.enums.SpellSchoolEnum

data class DomainSpell(
    val id: String,
    val name: String,
    val description: String,
    val spellMaterials: List<DomainSpellMaterial>,
    val components: String,
    val spellSchoolEnum: SpellSchoolEnum,
    val castingTime: String,
    val range: String,
    val duration: String,
    val level: SpellLevelEnum,
    val isRitual: Boolean
)
data class DomainSpellMaterial(
    val item: DomainItem,
    val consumed: Boolean
)

fun DomainSpell.toSpellEntity(): SpellEntity {
    val castingTimeParts = this.castingTime.split(" ", limit = 2)
    val castingTimeValue = castingTimeParts.getOrNull(0) ?: "0"
    val castingTimeUnit = castingTimeParts.getOrNull(1) ?: ""

    val rangeParts = this.range.split(" ", limit = 2)
    val rangeValue = rangeParts.getOrNull(0) ?: "0"
    val rangeUnit = try { RangeUnitEnum.valueOf(rangeParts.getOrNull(1) ?: "").name } catch (e: IllegalArgumentException) { "" }

    val durationParts = this.duration.split(" ", limit = 2)
    val durationValue = durationParts.getOrNull(0) ?: "0"
    val durationUnit = try { DurationUnitEnum.valueOf(durationParts.getOrNull(1) ?: "").name } catch (e: IllegalArgumentException) { "" }


    return SpellEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        spell_components = this.components, // V, S, M
        spell_level_enum = this.level,
        spell_school_enum = this.spellSchoolEnum,
        casting_time_value = castingTimeValue.toIntOrNull() ?: 0,
        casting_time_unit_enum = CastingTimeUnitEnum.valueOf(castingTimeUnit),
        range_value = rangeValue.toIntOrNull() ?: 0,
        range_unit_enum = RangeUnitEnum.valueOf(rangeUnit),
        duration_value = durationValue.toIntOrNull() ?: 0,
        duration_time_unit_enum = DurationUnitEnum.valueOf(durationUnit),
        is_ritual = isRitual

    )
}