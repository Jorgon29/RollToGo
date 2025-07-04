package com.terraplanistas.rolltogo.data.enums

enum class AbilityTypeEnum(val value: String) {
    STRENGTH("strength"),
    DEXTERITY("dexterity"),
    CONSTITUTION("constitution"),
    INTELLIGENCE("intelligence"),
    WISDOM("wisdom"),
    CHARISMA("charisma"),
    ALL("all");

    companion object {
        fun fromValue(value: String): AbilityTypeEnum? =
            entries.find { it.value == value }
    }
}