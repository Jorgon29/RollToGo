package com.terraplanistas.rolltogo.data.enums

enum class VisibilityEnum(val value: String) {
    PUBLIC("public"),
    PRIVATE("private"),
    UNLISTED("unlisted");

    companion object {
        fun fromValue(value: String): VisibilityEnum? =
            entries.find { it.value == value }
    }
}