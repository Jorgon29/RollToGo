package com.terraplanistas.rolltogo.data.enums

enum class SpellLevelEnum(val value: String) {
    CANTRIP("cantrip"),
    FIRST("first"),
    SECOND("second"),
    THIRD("third"),
    FOURTH("fourth"),
    FIFTH("fifth"),
    SIXTH("sixth"),
    SEVENTH("seventh"),
    EIGHTH("eighth"),
    NINTH("ninth");

    companion object {
        fun fromValue(value: String): SpellLevelEnum? =
            entries.find { it.value == value }
    }
}