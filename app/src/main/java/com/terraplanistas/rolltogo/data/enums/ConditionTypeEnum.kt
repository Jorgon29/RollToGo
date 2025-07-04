package com.terraplanistas.rolltogo.data.enums

enum class ConditionTypeEnum(val value: String) {
    BLINDED("blinded"),
    CHARMED("charmed"),
    DEAFENED("deafened"),
    FRIGHTENED("frightened"),
    GRAPPLED("grappled"),
    INCAPACITATED("incapacitated"),
    INVISIBLE("invisible"),
    PARALYZED("paralyzed"),
    PETRIFIED("petrified"),
    POISONED("poisoned"),
    PRONE("prone"),
    RESTRAINED("restrained"),
    STUNNED("stunned"),
    UNCONSCIOUS("unconscious"),
    OTHER("other");

    companion object {
        fun fromValue(value: String): ConditionTypeEnum? =
            entries.find { it.value == value }
    }
}