package com.terraplanistas.rolltogo.data.enums

enum class DurationUnitEnum(val value: String) {
    ROUNDS("rounds"),
    MINUTES("minutes"),
    HOURS("hours"),
    DAYS("days"),
    PERMANENT("permanent"),
    INSTANTANEOUS("instantaneous");

    companion object {
        fun fromValue(value: String): DurationUnitEnum? =
            entries.find { it.value == value }
    }
}