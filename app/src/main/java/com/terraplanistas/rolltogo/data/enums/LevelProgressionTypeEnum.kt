package com.terraplanistas.rolltogo.data.enums

enum class LevelProgressionTypeEnum(val value: String) {
    CLASS("class"),
    SUBCLASS("subclass"),
    FEAT("feat"),
    BACKGROUND("background"),
    ITEM("item"),
    OTHER("other");

    companion object {
        fun fromValue(value: String): LevelProgressionTypeEnum? =
            entries.find { it.value == value }
    }
}