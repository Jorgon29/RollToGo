package com.terraplanistas.rolltogo.data.enums

enum class MovementTypeEnum(val value: String) {
    WALKING("walking"),
    FLYING("flying"),
    SWIMMING("swimming"),
    CLIMBING("climbing"),
    BURROWING("burrowing"),
    OTHER("other");

    companion object {
        fun fromValue(value: String): MovementTypeEnum? =
            entries.find { it.value == value }
    }
}