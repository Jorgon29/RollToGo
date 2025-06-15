package com.terraplanistas.rolltogo.data.model.creatures.character

import com.terraplanistas.rolltogo.data.database.entities.creatures.CharactersEntity
import com.terraplanistas.rolltogo.data.database.entities.creatures.CreaturesEntity
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.AlignmentEnum
import com.terraplanistas.rolltogo.data.enums.CreatureSourceType

data class DomainCharacter(
    val id: String,
    val skills: List<DomainSkill>,
    val level: Int,
    val flaws: String,
    val biography: String,
    val appearance: String,
    val ideals: String,
    val age: String,
    val height: String,
    val weight: String,
    val personality: String,
    val gender: String,
    val skin: String,
    val hair: String,
    val faith: String,
    val eyes: String,
    val items: List<DomainItem>,
    val feats: List<DomainFeats>,
    val backgroundTitle: String,
    val backgroundDescription: String,
    val race: DomainRace,
    val hp: Int,
    val ac: Int,
    val spellcastingAbility: AbilityTypeEnum,
    val spells: List<DomainSpell>,
    val name: String,
    val characterClass: String,
    val alignment: AlignmentEnum,
    val abilities: List<DomainAbility>
)

fun DomainCharacter.toCharactersEntity(): CharactersEntity {
    return CharactersEntity(
        id = this.id,
        race_id = this.race.id,
        total_level = this.level,
        flaws = this.flaws,
        biography = this.biography,
        appearance = this.appearance,
        ideals = this.ideals,
        age = this.age,
        height = this.height,
        weight = this.weight,
        personality = this.personality,
        gender = this.gender,
        skin = this.skin,
        hair = this.hair,
        faith = this.faith,
        eyes = this.eyes
    )
}

fun DomainCharacter.toCreaturesEntity(): CreaturesEntity {
    return CreaturesEntity(
        id = this.id,
        name = this.name,
        size_enum = this.race.size,
        type_enum = this.race.type,
        alignment_enum = this.alignment,
        base_hp = this.hp,
        base_ac = this.ac,
        creature_source_type = CreatureSourceType.CHARACTER
    )
}