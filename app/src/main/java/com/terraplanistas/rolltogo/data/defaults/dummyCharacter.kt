package com.terraplanistas.rolltogo.data.defaults

import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.AlignmentEnum
import com.terraplanistas.rolltogo.data.enums.CreatureSizeEnum
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum
import com.terraplanistas.rolltogo.data.enums.ProficiencyLevelEnum
import com.terraplanistas.rolltogo.data.enums.SkillTypeEnum
import com.terraplanistas.rolltogo.data.enums.SpellLevelEnum
import com.terraplanistas.rolltogo.data.enums.SpellSchoolEnum
import com.terraplanistas.rolltogo.data.enums.ItemTypeEnum // NEW
import com.terraplanistas.rolltogo.data.enums.RarityEnum // NEW
import com.terraplanistas.rolltogo.data.enums.CurrencyEnum // NEW
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainAbility
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainCharacter
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainFeats
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainItem
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainRace
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainSkill
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainSpell
import java.math.BigDecimal
import java.util.UUID

fun getDummyCharacter(): DomainCharacter {
    val dummyRace = DomainRace(
        id = UUID.randomUUID().toString(),
        name = "Human",
        description = "A versatile and ambitious race.",
        size = CreatureSizeEnum.MEDIUM,
        type = CreatureTypeEnum.HUMANOID,
        languages = "common, elvish"
    )

    val dummySkill1 = DomainSkill(
        id = UUID.randomUUID().toString(),
        grantId = UUID.randomUUID().toString(),
        dieFormula = "1d20",
        skill = SkillTypeEnum.ACROBATICS,
        proficiency = ProficiencyLevelEnum.PROFICIENT,
    )

    val dummySkill2 = DomainSkill(
        id = UUID.randomUUID().toString(),
        grantId = UUID.randomUUID().toString(),
        dieFormula = "1d20",
        skill = SkillTypeEnum.PERCEPTION,
        proficiency = ProficiencyLevelEnum.PROFICIENT,
    )


    val dummyItem1 = DomainItem(
        id = UUID.randomUUID().toString(),
        grantId = UUID.randomUUID().toString(),
        name = "Longsword",
        description = "A standard one-handed sword.",
        item_type_enum = ItemTypeEnum.WEAPON,
        rarity_enum = RarityEnum.COMMON,
        weight = BigDecimal("3.0"),
        cost_value = BigDecimal("15.00"),
        cost_unit = CurrencyEnum.GOLD,
        attunement_required = false,
        it_magical = false
    )

    val dummyItem2 = DomainItem(
        id = UUID.randomUUID().toString(),
        grantId = UUID.randomUUID().toString(),
        name = "Leather Armor",
        description = "Light armor made from hardened leather.",
        item_type_enum = ItemTypeEnum.ARMOR,
        rarity_enum = RarityEnum.COMMON,
        weight = BigDecimal("5.0"),
        cost_value = BigDecimal("10.00"),
        cost_unit = CurrencyEnum.GOLD,
        attunement_required = false,
        it_magical = false
    )

    val dummyFeat1 = DomainFeats(
        id = UUID.randomUUID().toString(),
        grantId = UUID.randomUUID().toString(),
        name = "Sentinel",
        description = "You are a master of opportunity attacks, using them to control the battlefield."
    )

    val dummySpell1 = DomainSpell(
        id = UUID.randomUUID().toString(),
        grantId = UUID.randomUUID().toString(),
        name = "Magic Missile",
        description = "You create three glowing darts of magical force.",
        level = SpellLevelEnum.FIRST,
        spellSchoolEnum = SpellSchoolEnum.EVOCATION,
        castingTime = "1 Action",
        range = "120 feet",
        components = "V, S",
        duration = "Instantaneous",
        isRitual = false, // Added
        spellMaterials = emptyList()
    )

    val dummyStrength = DomainAbility(
        id = UUID.randomUUID().toString(),
        abilityEnum = AbilityTypeEnum.STRENGTH,
        modifier = 2,
        grantId = UUID.randomUUID().toString(),
        value = 14
    )
    val dummyDexterity = DomainAbility(
        id = UUID.randomUUID().toString(),
        abilityEnum = AbilityTypeEnum.DEXTERITY,
        modifier = 3,
        grantId = UUID.randomUUID().toString(),
        value = 16
    )
    val dummyConstitution = DomainAbility(
        id = UUID.randomUUID().toString(),
        abilityEnum = AbilityTypeEnum.CONSTITUTION,
        modifier = 1,
        grantId = UUID.randomUUID().toString(),
        value = 12
    )
    val dummyIntelligence = DomainAbility(
        id = UUID.randomUUID().toString(),
        abilityEnum = AbilityTypeEnum.INTELLIGENCE,
        modifier = 0,
        grantId = UUID.randomUUID().toString(),
        value = 10
    )
    val dummyWisdom = DomainAbility(
        id = UUID.randomUUID().toString(),
        abilityEnum = AbilityTypeEnum.WISDOM,
        modifier = 1,
        grantId = UUID.randomUUID().toString(),
        value = 12
    )
    val dummyCharisma = DomainAbility(
        id = UUID.randomUUID().toString(),
        abilityEnum = AbilityTypeEnum.CHARISMA,
        modifier = 2,
        grantId = UUID.randomUUID().toString(),
        value = 14
    )


    return DomainCharacter(
        id = UUID.randomUUID().toString(),
        level = 1,
        flaws = "Overly trusting of strangers; prone to boasting.",
        biography = "Born in a quiet village, spent youth dreaming of adventure. Left home to find fame and fortune.",
        appearance = "Average height, lean build, with a scar above the left eyebrow.",
        ideals = "Freedom: Tyranny is anathema to me. (Chaotic Good)",
        age = "22",
        height = "5' 9\"",
        weight = "160 lbs",
        personality = "Optimistic and quick to make friends, but can be naive.",
        gender = "Female",
        skin = "Fair",
        hair = "Brown, shoulder-length",
        faith = "Tymora, Goddess of Luck",
        eyes = "Green",
        name = "Elara Swiftfoot",
        hp = 10,
        ac = 14,
        race = dummyRace,
        backgroundTitle = "Folk Hero",
        backgroundDescription = "You stood up to a local tyrant, securing your place in the hearts of the common folk.",
        characterClass = "Fighter",
        spellcastingAbility = AbilityTypeEnum.ALL,
        skills = listOf(dummySkill1, dummySkill2),
        items = listOf(dummyItem1, dummyItem2),
        feats = listOf(dummyFeat1),
        spells = listOf(dummySpell1),
        alignment = AlignmentEnum.CHAOTIC_GOOD,
        abilities = listOf(dummyStrength, dummyDexterity, dummyConstitution, dummyIntelligence, dummyWisdom, dummyCharisma)
    )
}