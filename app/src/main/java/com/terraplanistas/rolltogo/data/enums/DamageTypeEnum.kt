package com.terraplanistas.rolltogo.data.enums

enum class DamageTypeEnum(val value: String) {
    ACID("acid"),
    BLUDGEONING("bludgeoning"),
    COLD("cold"),
    FIRE("fire"),
    FORCE("force"),
    LIGHTNING("lightning"),
    NECROTIC("necrotic"),
    PIERCING("piercing"),
    POISON("poison"),
    PSYCHIC("psychic"),
    RADIANT("radiant"),
    SLASHING("slashing"),
    THUNDER("thunder");

    companion object {
        fun fromValue(value: String): DamageTypeEnum? =
            entries.find { it.value == value }
    }
}