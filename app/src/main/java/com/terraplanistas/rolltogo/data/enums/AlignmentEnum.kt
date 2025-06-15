package com.terraplanistas.rolltogo.data.enums

enum class AlignmentEnum(val value: String) {
    LAWFUL_GOOD("lawful_good"),
    NEUTRAL_GOOD("neutral_good"),
    CHAOTIC_GOOD("chaotic_good"),
    LAWFUL_NEUTRAL("lawful_neutral"),
    TRUE_NEUTRAL("true_neutral"),
    CHAOTIC_NEUTRAL("chaotic_neutral"),
    LAWFUL_EVIL("lawful_evil"),
    NEUTRAL_EVIL("neutral_evil"),
    CHAOTIC_EVIL("chaotic_evil");

    companion object {
        fun fromValue(value: String): AlignmentEnum? =
            entries.find { it.value == value }
    }
}