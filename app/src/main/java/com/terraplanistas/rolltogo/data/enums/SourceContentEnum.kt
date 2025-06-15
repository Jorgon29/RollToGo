package com.terraplanistas.rolltogo.data.enums

enum class SourceContentEnum(val value: String) {
    ITEM("item"),
    BACKGROUND("background"),
    CLASS("class"),
    SUBCLASS("subclass"),
    PROFICIENCIES("proficiencies"),
    BONUSES("bonuses"),
    ABILITIES("abilities"),
    SKILLS("skills"),
    CREATURES("Creatures"),
    SPELLS("spells"),
    SPECIES("species"),
    SUBSPECIES("subspecies"),
    FEATS("feats"),
    LIMITED_USAGES("limited_usages"),
    EFFECTS("effects"),
    ACTIONS("actions"),
    DAMAGES("damages"),
    SENSES("senses"),
    ABILITY_SCORE_IMPROVEMENT("ability_score_improvement"),
    MOVEMENTS("movements"),
    ROOMS("rooms");

    companion object {
        fun fromValue(value: String): SourceContentEnum? =
            entries.find { it.value == value }
    }
}