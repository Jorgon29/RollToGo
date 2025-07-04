package com.terraplanistas.rolltogo.data.enums

enum class SpellcastingProgressionEnum(val value: String) {
    FULL_CASTER("full_caster"),
    HALF_CASTER("half_caster"),
    THIRD_CASTER("third_caster"),
    PACT_MAGIC("pact_magic"),
    INNATE_MAGIC("innate_magic");

    companion object {
        fun fromValue(value: String): SpellcastingProgressionEnum? =
            entries.find { it.value == value }
    }
}