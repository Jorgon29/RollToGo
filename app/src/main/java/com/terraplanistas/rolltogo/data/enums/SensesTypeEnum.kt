package com.terraplanistas.rolltogo.data.enums

enum class SensesTypeEnum(val value: String) {
    BLINDSIGHT("blindsight"),
    DARKVISION("darkvision"),
    TREMORSENSE("tremorsense"),
    TRUESIGHT("truesight"),
    OTHER("other");

    companion object {
        fun fromValue(value: String): SensesTypeEnum? =
            entries.find { it.value == value }
    }
}