package com.terraplanistas.rolltogo.data.enums

enum class RecoveryTypeEnum(val value: String) {
    SHORT_REST("short_rest"),
    LONG_REST("long_rest"),
    DAILY("daily"),
    PERMANENT("permanent"),
    OTHER("other");

    companion object {
        fun fromValue(value: String): RecoveryTypeEnum? =
            entries.find { it.value == value }
    }
}