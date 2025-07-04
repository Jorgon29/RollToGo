package com.terraplanistas.rolltogo.data.enums

enum class CreatureSizeEnum(val value: String) {
    TINY("tiny"),
    SMALL("small"),
    MEDIUM("medium"),
    LARGE("large"),
    HUGE("huge"),
    GARGANTUAN("gargantuan");

    companion object {
        fun fromValue(value: String): CreatureSizeEnum? =
            entries.find { it.value == value }
    }
}