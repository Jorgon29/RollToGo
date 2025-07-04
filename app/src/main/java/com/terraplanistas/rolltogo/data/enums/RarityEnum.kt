package com.terraplanistas.rolltogo.data.enums

enum class RarityEnum(val value: String) {
    COMMON("common"),
    UNCOMMON("uncommon"),
    RARE("rare"),
    VERY_RARE("very_rare"),
    LEGENDARY("legendary"),
    ARTIFACT("artifact");

    companion object {
        fun fromValue(value: String): RarityEnum? =
            entries.find { it.value == value }
    }
}