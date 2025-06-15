package com.terraplanistas.rolltogo.data.enums

enum class CastingTimeUnitEnum(val value: String) {
    ACTION("action"),
    BONUS_ACTION("bonus action"),
    REACTION("reaction"),
    MINUTE("minute"),
    HOUR("hour"),
    DAY("day"),
    TURN("turn");

    companion object {
        fun fromValue(value: String): CastingTimeUnitEnum? =
            entries.find { it.value == value }
    }
}