package com.terraplanistas.rolltogo.data.enums

enum class CreatureTypeEnum(val value: String) {
    ABERRATION("aberration"),
    BEAST("beast"),
    CELESTIAL("celestial"),
    CONSTRUCT("construct"),
    DRAGON("dragon"),
    ELEMENTAL("elemental"),
    FEY("fey"),
    FIEND("fiend"),
    GIANT("giant"),
    HUMANOID("humanoid"),
    MONSTROSITY("monstrosity"),
    OOZE("ooze"),
    PLANT("plant"),
    UNDEAD("undead"),
    OTHER("other");

    companion object {
        fun fromValue(value: String): CreatureTypeEnum? =
            entries.find { it.value == value }
    }
}