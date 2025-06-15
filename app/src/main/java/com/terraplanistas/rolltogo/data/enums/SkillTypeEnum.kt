package com.terraplanistas.rolltogo.data.enums

enum class SkillTypeEnum(val value: String) {
    ACROBATICS("acrobatics"),
    ANIMAL_HANDLING("animal_handling"),
    ARCANA("arcana"),
    ATHLETICS("athletics"),
    DECEPTION("deception"),
    HISTORY("history"),
    INSIGHT("insight"),
    INTIMIDATION("intimidation"),
    INVESTIGATION("investigation"),
    MEDICINE("medicine"),
    NATURE("nature"),
    PERCEPTION("perception"),
    PERFORMANCE("performance"),
    PERSUASION("persuasion"),
    RELIGION("religion"),
    SLEIGHT_OF_HAND("sleight_of_hand"),
    STEALTH("stealth"),
    SURVIVAL("survival");

    companion object {
        fun fromValue(value: String): SkillTypeEnum? =
            entries.find { it.value == value }
    }
}