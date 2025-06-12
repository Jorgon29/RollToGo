package com.terraplanistas.rolltogo.data.enums

enum class SkillEnum(val linkedAbility: AbilityEnum) {
    ATHLETICS(AbilityEnum.STRENGTH),
    ACROBATICS(AbilityEnum.DEXTERITY),
    SLEIGHT_OF_HAND(AbilityEnum.DEXTERITY),
    STEALTH(AbilityEnum.DEXTERITY),
    ARCANA(AbilityEnum.INTELLIGENCE),
    HISTORY(AbilityEnum.INTELLIGENCE),
    INVESTIGATION(AbilityEnum.INTELLIGENCE),
    NATURE(AbilityEnum.INTELLIGENCE),
    RELIGION(AbilityEnum.INTELLIGENCE),
    ANIMAL_HANDLING(AbilityEnum.WISDOM),
    INSIGHT(AbilityEnum.WISDOM),
    MEDICINE(AbilityEnum.WISDOM),
    PERCEPTION(AbilityEnum.WISDOM),
    SURVIVAL(AbilityEnum.WISDOM),
    DECEPTION(AbilityEnum.CHARISMA),
    INTIMIDATION(AbilityEnum.CHARISMA),
    PERFORMANCE(AbilityEnum.CHARISMA),
    PERSUASION(AbilityEnum.CHARISMA);
}