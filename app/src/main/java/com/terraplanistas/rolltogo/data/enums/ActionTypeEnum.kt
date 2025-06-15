package com.terraplanistas.rolltogo.data.enums

enum class ActionTypeEnum(val value: String) {
    ACTION("action"),
    BONUS_ACTION("bonus_action"),
    REACTION("reaction"),
    LEGENDARY_ACTION("legendary_action"),
    LAIR_ACTION("lair_action"),
    MYTHIC_ACTION("mythic_action"),
    SPECIAL_ACTION("special_action");

    companion object {
        fun fromValue(value: String): ActionTypeEnum? =
            entries.find { it.value == value }
    }
}