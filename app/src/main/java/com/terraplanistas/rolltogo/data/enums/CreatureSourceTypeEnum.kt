package com.terraplanistas.rolltogo.data.enums

enum class CreatureSourceType(val value: String) {
    MONSTER("monster"),
    CHARACTER("character"),
    INVOCATION("invocation"),
    VEHICLE("vehicle");

    companion object {
        fun fromValue(value: String): CreatureSourceType? =
            entries.find { it.value == value }
    }
}