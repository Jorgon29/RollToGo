package com.terraplanistas.rolltogo.data.enums

enum class ItemModifierTypeEnum(val value: String) {
    BONUS("bonus"),
    PENALTY("penalty"),
    REDUCTION("reduction"),
    TRANSFORMATION("transformation");

    companion object {
        fun fromValue(value: String): ItemModifierTypeEnum? =
            entries.find { it.value == value }
    }
}