package com.terraplanistas.rolltogo.data.enums

enum class RoleEnum(val value: String) {
    DUNGEON_MASTER("dungeon_master"),
    PLAYER("player");

    companion object {
        fun fromValue(value: String): RoleEnum? =
            entries.find { it.value == value }
    }
}