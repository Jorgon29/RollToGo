package com.terraplanistas.rolltogo.data.enums

enum class BonusTypeEnum(val value: String) {
    ATTACK_ROLL("attack_roll"),
    DAMAGE_ROLL("damage_roll"),
    SAVING_THROW("saving_throw"),
    ABILITY_CHECK("ability_check"),
    SKILL_CHECK("skill_check"),
    SPELL_ATTACK_BONUS("spell_attack_bonus"),
    INITIATIVE("initiative");

    companion object {
        fun fromValue(value: String): BonusTypeEnum? =
            entries.find { it.value == value }
    }
}