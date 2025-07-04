package com.terraplanistas.rolltogo.data.enums

enum class CoverTypeEnum(val value: String) {
    NONE("none"),
    HALF("half"),
    THREE_QUARTERS("three_quarters"),
    FULL("full");

    companion object {
        fun fromValue(value: String): CoverTypeEnum? =
            entries.find { it.value == value }
    }
}