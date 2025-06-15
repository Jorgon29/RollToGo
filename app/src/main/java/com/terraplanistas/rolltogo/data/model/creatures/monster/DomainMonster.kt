package com.terraplanistas.rolltogo.data.model.creatures.monster

import com.terraplanistas.rolltogo.data.database.entities.creatures.CreaturesEntity
import com.terraplanistas.rolltogo.data.database.entities.creatures.MonstersEntity
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.AlignmentEnum
import com.terraplanistas.rolltogo.data.enums.CreatureSizeEnum
import com.terraplanistas.rolltogo.data.enums.CreatureSourceType
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainAbility
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainFeats
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainItem
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainSkill
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainSpell

data class DomainMonster(
    val id: String,
    val skills: List<DomainSkill>,
    val items: List<DomainItem>,
    val feats: List<DomainFeats>,
    val hp: Int,
    val ac: Int,
    val size: CreatureSizeEnum,
    val type: CreatureTypeEnum,
    val spellcastingAbility: AbilityTypeEnum,
    val spells: List<DomainSpell>,
    val name: String,
    val alignment: AlignmentEnum,
    val challengeRating: String,
    val legendary: Boolean,
    val lair: Boolean,
    val abilities: List<DomainAbility>
)

fun DomainMonster.toCreatureEntity(): CreaturesEntity{
    return CreaturesEntity(
        id = id,
        name = name,
        size_enum = size,
        type_enum = type,
        alignment_enum = alignment,
        base_hp = hp,
        base_ac = ac,
        creature_source_type = CreatureSourceType.MONSTER
    )
}

fun DomainMonster.toMonsterEntity(): MonstersEntity {
    return MonstersEntity(
        id = id,
        challenge_rating = challengeRating,
        legendary = legendary,
        lair = lair
    )
}
