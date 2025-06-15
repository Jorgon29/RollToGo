package com.terraplanistas.rolltogo.data.enums

enum class RangeUnitEnum(val value: String) {
    FEET("feet"),
    SELF("self"),
    TOUCH("touch"),
    MILES("miles"),
    UNLIMITED("unlimited");

    companion object {
        fun fromValue(value: String): RangeUnitEnum? =
            entries.find { it.value == value }
    }
}