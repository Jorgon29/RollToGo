package com.terraplanistas.rolltogo.data.enums

enum class ItemTypeEnum(val value: String) {
    WEAPON("weapon"),
    ARMOR("armor"),
    ACCESSORY("accessory"),
    CONSUMABLE("consumable"),
    TOOL("tool"),
    MISCELLANEOUS("miscellaneous");

    companion object {
        fun fromValue(value: String): ItemTypeEnum? =
            entries.find { it.value == value }
    }
}