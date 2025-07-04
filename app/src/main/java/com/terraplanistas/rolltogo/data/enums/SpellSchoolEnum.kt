package com.terraplanistas.rolltogo.data.enums

enum class SpellSchoolEnum(val value: String) {
    ABJURATION("abjuration"),
    CONJURATION("conjuration"),
    DIVINATION("divination"),
    ENCHANTMENT("enchantment"),
    EVOCATION("evocation"),
    ILLUSION("illusion"),
    NECROMANCY("necromancy"),
    TRANSMUTATION("transmutation"),
    UNIVERSAL("universal");

    companion object {
        fun fromValue(value: String): SpellSchoolEnum? =
            entries.find { it.value == value }
    }
}