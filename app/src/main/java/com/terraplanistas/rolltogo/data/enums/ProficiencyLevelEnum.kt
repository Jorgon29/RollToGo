package com.terraplanistas.rolltogo.data.enums

enum class ProficiencyLevelEnum(val value: String) {
    PROFICIENT("proficient"),
    EXPERTISE("expertise"),
    HALF_PROFICIENT("half_proficient"),
    NOT_PROFICIENT("not_proficient");

    companion object {
        fun fromValue(value: String): ProficiencyLevelEnum? =
            entries.find { it.value == value }
    }
}