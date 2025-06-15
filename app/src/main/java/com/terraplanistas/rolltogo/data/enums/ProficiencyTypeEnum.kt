package com.terraplanistas.rolltogo.data.enums

enum class ProficiencyTypeEnum(val value: String) {
    WEAPON("weapon"),
    ARMOR("armor"),
    TOOL("tool"),
    SKILL("skill"),
    SAVING_THROW("saving_throw"),
    LANGUAGE("language"),
    OTHER("other");

    companion object {
        fun fromValue(value: String): ProficiencyTypeEnum? =
            entries.find { it.value == value }
    }
}