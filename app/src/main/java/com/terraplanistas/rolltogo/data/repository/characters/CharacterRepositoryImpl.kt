package com.terraplanistas.rolltogo.data.repository.characters

import com.terraplanistas.rolltogo.data.database.dao.classDao.ClassDao
import com.terraplanistas.rolltogo.data.database.dao.classDao.SpellcastingDao
import com.terraplanistas.rolltogo.data.database.dao.creatures.CharactersDao
import com.terraplanistas.rolltogo.data.database.dao.creatures.CreaturesDao
import com.terraplanistas.rolltogo.data.database.dao.grants.GrantsDao
import com.terraplanistas.rolltogo.data.database.dao.items.ItemDao
import com.terraplanistas.rolltogo.data.database.dao.items.ItemTagDao
import com.terraplanistas.rolltogo.data.database.dao.misc.AbilitiesDao
import com.terraplanistas.rolltogo.data.database.dao.misc.BackgroundDao
import com.terraplanistas.rolltogo.data.database.dao.misc.FeatsDao
import com.terraplanistas.rolltogo.data.database.dao.misc.SkillDao
import com.terraplanistas.rolltogo.data.database.dao.species.SpeciesDao
import com.terraplanistas.rolltogo.data.database.dao.spells.SpellDao
import com.terraplanistas.rolltogo.data.database.dao.spells.SpellMaterialDao
import com.terraplanistas.rolltogo.data.database.entities.creatures.CharactersEntity
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantsEntity
import com.terraplanistas.rolltogo.data.database.entities.items.toDomainItem
import com.terraplanistas.rolltogo.data.database.entities.misc.toDomainAbility
import com.terraplanistas.rolltogo.data.database.entities.misc.toDomainFeats
import com.terraplanistas.rolltogo.data.database.entities.misc.toDomainSkill
import com.terraplanistas.rolltogo.data.database.entities.species.toDomainRace
import com.terraplanistas.rolltogo.data.database.entities.spells.SpellMaterialEntity
import com.terraplanistas.rolltogo.data.database.entities.spells.toDomainSpell
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.AlignmentEnum
import com.terraplanistas.rolltogo.data.enums.CreatureSizeEnum
import com.terraplanistas.rolltogo.data.enums.CreatureSourceType
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum
import com.terraplanistas.rolltogo.data.enums.ProficiencyLevelEnum
import com.terraplanistas.rolltogo.data.enums.SkillTypeEnum
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.data.enums.VisibilityEnum
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainAbility
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainCharacter
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainFeats
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainItem
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainRace
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainSkill
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainSpell
import com.terraplanistas.rolltogo.data.model.creatures.character.toAbilityEntity
import com.terraplanistas.rolltogo.data.model.creatures.character.toCharactersEntity
import com.terraplanistas.rolltogo.data.model.creatures.character.toCreaturesEntity
import com.terraplanistas.rolltogo.data.model.creatures.character.toItemEntity
import com.terraplanistas.rolltogo.data.model.creatures.character.toSkillEntity
import com.terraplanistas.rolltogo.data.model.creatures.character.toSpellEntity
import com.terraplanistas.rolltogo.data.remote.RetrofitInstance
import com.terraplanistas.rolltogo.data.remote.RetrofitInstance.characterService
import com.terraplanistas.rolltogo.data.remote.dtos.AbilityCreateRequest
import com.terraplanistas.rolltogo.data.remote.dtos.CharacterCreateRequest
import com.terraplanistas.rolltogo.data.remote.dtos.ContentCreateRequest
import com.terraplanistas.rolltogo.data.remote.dtos.CreatureCreateRequest
import com.terraplanistas.rolltogo.data.remote.dtos.GrantCreateRequest
import com.terraplanistas.rolltogo.data.remote.dtos.SkillCreateRequest
import com.terraplanistas.rolltogo.helpers.Resource
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationContext
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.withContext
import java.util.UUID
class CharacterRepositoryImpl(
    private val charactersDao: CharactersDao,
    private val creaturesDao: CreaturesDao,
    private val speciesDao: SpeciesDao,
    private val classDao: ClassDao,
    private val grantsDao: GrantsDao,
    private val itemDao: ItemDao,
    private val itemTagDao: ItemTagDao,
    private val featsDao: FeatsDao,
    private val skillDao: SkillDao,
    private val spellDao: SpellDao,
    private val spellcastingDao: SpellcastingDao,
    private val spellMaterialDao: SpellMaterialDao,
    private val backgroundDao: BackgroundDao,
    private val abilitiesDao: AbilitiesDao
) : CharacterRepository {

    private suspend fun buildDomainCharacter(charEntity: CharactersEntity): DomainCharacter? {
        val creatureEntity = creaturesDao.getCreatureById(charEntity.id).firstOrNull()
            ?: return null


        val raceEntity = speciesDao.getSpeciesById(charEntity.race_id).firstOrNull()
        val domainRace = raceEntity?.toDomainRace() ?: DomainRace(
            "", "Unknown Race", "", "",
            size = CreatureSizeEnum.MEDIUM,
            type = CreatureTypeEnum.HUMANOID,
        )

        val backgroundGrant = grantsDao.getGrantsByGranterContentId(charEntity.id)
            .firstOrNull()
            ?.find { it.granted_type == SourceContentEnum.BACKGROUND }
        val backgroundEntity = backgroundGrant?.let {
            backgroundDao.getBackgroundById(it.granted_content_id).firstOrNull()
        }
        val backgroundTitle = backgroundEntity?.name ?: "N/A"
        val backgroundDescription = backgroundEntity?.description ?: "N/A"

        var characterClass = "Unknown"
        var spellcastingAbility: AbilityTypeEnum? = null

        val classGrant = grantsDao.getGrantsByGranterContentId(charEntity.id)
            .firstOrNull()
            ?.find { it.granted_type == SourceContentEnum.CLASS }
        classGrant?.let {
            val classEntity = classDao.getClassById(it.granted_content_id).firstOrNull()
            characterClass = classEntity?.name ?: "Unknown"

            val spellcastingEntity = spellcastingDao.getSpellcastingByClassId(it.granted_content_id).firstOrNull()
            spellcastingAbility = spellcastingEntity?.firstOrNull()?.spell_casting_ability

        }

        val allGrantsForCharacter = grantsDao.getGrantsByGranterContentId(charEntity.id).firstOrNull() ?: emptyList()



        val domainItems = mutableListOf<DomainItem>()
        val itemGrants = allGrantsForCharacter.filter { it.granted_type == SourceContentEnum.ITEM }
        for (itemGrant in itemGrants) {
            val itemEntity = itemDao.getItemById(itemGrant.granted_content_id).firstOrNull()
            itemEntity?.let {
                val tags = itemTagDao.getTagsByItemId(it.id).firstOrNull()
                val tagStrings = tags?.map { it.tag } ?: emptyList()
                domainItems.add(it.toDomainItem(tagStrings, grantId = itemGrant.id))
            }
        }

        val domainFeats = mutableListOf<DomainFeats>()
        val featGrants = allGrantsForCharacter.filter { it.granted_type == SourceContentEnum.FEATS }
        for (featGrant in featGrants) {
            val featEntity = featsDao.getFeatById(featGrant.granted_content_id).firstOrNull()
            featEntity?.let {
                domainFeats.add(it.toDomainFeats(grantId = featGrant.id))
            }
        }

        val domainSpells = mutableListOf<DomainSpell>()
        val spellGrants = allGrantsForCharacter.filter { it.granted_type == SourceContentEnum.SPELLS }
        for (spellGrant in spellGrants) {
            val spellEntity = spellDao.getSpellById(spellGrant.granted_content_id).firstOrNull()
            spellEntity?.let { spell ->
                val materialEntities = spellMaterialDao.getMaterialsForSpell(spell.id).firstOrNull() ?: emptyList()
                domainSpells.add(spell.toDomainSpell(materialEntities, itemDao, grantId = spellGrant.id))
            }
        }

        val domainAbilities = mutableListOf<DomainAbility>()
        val abilityGrants = allGrantsForCharacter.filter { it.granted_type == SourceContentEnum.ABILITIES }
        for (abilityGrant in abilityGrants) {
            val abilityEntity = abilitiesDao.getAbilityById(abilityGrant.granted_content_id).firstOrNull()
            abilityEntity?.let {
                domainAbilities.add(it.toDomainAbility(grantId = abilityGrant.id))
            }
        }


        val domainSkills = mutableListOf<DomainSkill>()
        domainAbilities.forEach { ability ->
            skillDao.getSkillsByAbilityId(ability.id)
                .mapNotNull { skillEntities ->
                    skillEntities.map { it.toDomainSkill() }
                }
                .collect { skillsForAbility ->
                    domainSkills.addAll(skillsForAbility)
                }
        }

        return DomainCharacter(
            id = charEntity.id,
            level = charEntity.total_level,
            flaws = charEntity.flaws,
            biography = charEntity.biography,
            appearance = charEntity.appearance,
            ideals = charEntity.ideals,
            age = charEntity.age,
            height = charEntity.height,
            weight = charEntity.weight,
            personality = charEntity.personality,
            gender = charEntity.gender,
            skin = charEntity.skin,
            hair = charEntity.hair,
            faith = charEntity.faith,
            eyes = charEntity.eyes,
            name = creatureEntity.name,
            hp = creatureEntity.base_hp,
            ac = creatureEntity.base_ac,
            race = domainRace,
            backgroundTitle = backgroundTitle,
            backgroundDescription = backgroundDescription,
            characterClass = characterClass,
            spellcastingAbility = spellcastingAbility ?: AbilityTypeEnum.ALL,
            skills = domainSkills,
            items = domainItems,
            feats = domainFeats,
            spells = domainSpells,
            alignment = creatureEntity.alignment_enum,
            abilities = domainAbilities // ADDED
        )
    }

    override fun getCharacterById(id: String): Flow<Resource<DomainCharacter>> = flow {
        emit(Resource.Loading)

        try {
            val domainCharacter = withContext(Dispatchers.IO) {
                charactersDao.getCharacterById(id).firstOrNull()?.let { charEntity ->
                    buildDomainCharacter(charEntity)
                }
            }

            if (domainCharacter != null) {
                emit(Resource.Success(domainCharacter))
            } else {
                emit(Resource.Error("Character with ID $id not found."))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error fetching character: ${e.localizedMessage ?: "Unknown error"}"))
        }
    }

    override fun searchCharacters(query: String): Flow<Resource<List<DomainCharacter>>> = flow {
        emit(Resource.Loading)

        try {
            val characterEntities = withContext(Dispatchers.IO) {
                charactersDao.searchCharactersByName(query).firstOrNull() ?: emptyList()
            }

            val domainCharacters = characterEntities.mapNotNull { charEntity ->
                withContext(Dispatchers.IO) {
                    buildDomainCharacter(charEntity)
                }
            }
            emit(Resource.Success(domainCharacters))
        } catch (e: Exception) {
            emit(Resource.Error("Error searching characters: ${e.localizedMessage ?: "Unknown error"}"))
        }
    }

    override suspend fun deleteCharacter(characterId: String): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val characterExists = charactersDao.getCharacterById(characterId).firstOrNull() != null
                if (!characterExists) {
                    return@withContext Resource.Error("Character with ID $characterId not found.")
                }
                charactersDao.deleteCharacterById(characterId)
                val grantsToDelete = grantsDao.getGrantsByGranterContentId(characterId).firstOrNull() ?: emptyList()
                for(grant in grantsToDelete) {
                    grantsDao.deleteGrant(grant)
                }
                Resource.Success(Unit)
            } catch (e: Exception) {
                Resource.Error("Error deleting character: ${e.localizedMessage ?: "Unknown error"}")
            }
        }
    }

    override suspend fun saveCharacter(character: DomainCharacter): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val characterEntity = character.toCharactersEntity()
                val creatureEntity = character.toCreaturesEntity()

                charactersDao.insertCharacter(characterEntity)
                creaturesDao.insertCreature(creatureEntity)

                val allCurrentGrants = grantsDao.getGrantsByGranterContentId(character.id)
                    .firstOrNull() ?: emptyList()

                val existingBackgroundGrant = allCurrentGrants.find { it.granted_type == SourceContentEnum.BACKGROUND }

                if (character.backgroundTitle != "N/A" || character.backgroundDescription != "N/A") {
                    val backgroundId = backgroundDao.getBackgroundByName(character.backgroundTitle)
                        .firstOrNull()?.id
                    if (backgroundId != null) {
                        val newGrantId = existingBackgroundGrant?.id ?: UUID.randomUUID().toString()
                        val newBackgroundGrant = GrantsEntity(
                            id = newGrantId,
                            granter_type_enum = SourceContentEnum.CREATURES,
                            granter_content_id = character.id,
                            granted_type = SourceContentEnum.BACKGROUND,
                            granted_content_id = backgroundId
                        )
                        grantsDao.insertGrant(newBackgroundGrant)
                    }
                } else if (existingBackgroundGrant != null) {
                    grantsDao.deleteGrant(existingBackgroundGrant)
                }

                val currentItemGrants = allCurrentGrants.filter { it.granted_type == SourceContentEnum.ITEM }
                val currentGrantedItemIds = currentItemGrants.map { it.granted_content_id }.toSet()

                for (domainItem in character.items) {
                    val itemEntity = domainItem.toItemEntity()
                    itemDao.insertItem(itemEntity)
                    if (!currentGrantedItemIds.contains(domainItem.id)) {
                        grantsDao.insertGrant(GrantsEntity(
                            id = domainItem.grantId ?: UUID.randomUUID().toString(),
                            granter_type_enum = SourceContentEnum.CREATURES,
                            granter_content_id = character.id,
                            granted_type = SourceContentEnum.ITEM,
                            granted_content_id = domainItem.id
                        ))
                    }
                }

                for(domainSkill in character.skills){
                    skillDao.insertSkill(domainSkill.toSkillEntity())
                }

                val domainItemContentIds = character.items.map { it.id }.toSet()
                val itemsToRemove = currentItemGrants.filter { it.granted_content_id !in domainItemContentIds }
                for (grantToDelete in itemsToRemove) {
                    grantsDao.deleteGrant(grantToDelete)
                }

                val currentFeatGrants = allCurrentGrants.filter { it.granted_type == SourceContentEnum.FEATS }
                val currentGrantedFeatIds = currentFeatGrants.map { it.granted_content_id }.toSet()

                for (domainFeat in character.feats) {
                    if (!currentGrantedFeatIds.contains(domainFeat.id)) {
                        grantsDao.insertGrant(GrantsEntity(
                            id = domainFeat.grantId ?: UUID.randomUUID().toString(),
                            granter_type_enum = SourceContentEnum.CREATURES,
                            granter_content_id = character.id,
                            granted_type = SourceContentEnum.FEATS,
                            granted_content_id = domainFeat.id
                        ))
                    }
                }

                val domainFeatContentIds = character.feats.map { it.id }.toSet()
                val featsToRemove = currentFeatGrants.filter { it.granted_content_id !in domainFeatContentIds }
                for (grantToDelete in featsToRemove) {
                    grantsDao.deleteGrant(grantToDelete)
                }

                val currentSpellGrants = allCurrentGrants.filter { it.granted_type == SourceContentEnum.SPELLS }
                val currentGrantedSpellIds = currentSpellGrants.map { it.granted_content_id }.toSet()

                for (domainSpell in character.spells) {
                    val spellEntity = domainSpell.toSpellEntity()
                    spellDao.insertSpell(spellEntity)

                    val currentMaterialEntities = spellMaterialDao.getMaterialsForSpell(spellEntity.id).firstOrNull() ?: emptyList()
                    val newMaterialComponents = domainSpell.spellMaterials

                    val currentMaterialMap = currentMaterialEntities.associateBy { it.item_id }

                    for (newComp in newMaterialComponents) {
                        val existingComp = currentMaterialMap[newComp.item.id]
                        if (existingComp == null || existingComp.consumed != newComp.consumed) {
                            spellMaterialDao.insertSpellMaterial(
                                SpellMaterialEntity(
                                    spell_id = spellEntity.id,
                                    item_id = newComp.item.id,
                                    consumed = newComp.consumed
                                )
                            )
                        }
                        itemDao.insertItem(newComp.item.toItemEntity())
                    }

                    val newMaterialItemIds = newMaterialComponents.map { it.item.id }.toSet()
                    val materialsToRemove = currentMaterialEntities.filter { it.item_id !in newMaterialItemIds }
                    for (matToDelete in materialsToRemove) {
                        spellMaterialDao.deleteSpellMaterial(matToDelete)
                    }

                    if (!currentGrantedSpellIds.contains(domainSpell.id)) {
                        grantsDao.insertGrant(GrantsEntity(
                            id = domainSpell.grantId ?: UUID.randomUUID().toString(),
                            granter_type_enum = SourceContentEnum.CREATURES,
                            granter_content_id = character.id,
                            granted_type = SourceContentEnum.SPELLS,
                            granted_content_id = domainSpell.id
                        ))
                    }
                }

                val domainSpellContentIds = character.spells.map { it.id }.toSet()
                val spellsToRemove = currentSpellGrants.filter { it.granted_content_id !in domainSpellContentIds }
                for (grantToDelete in spellsToRemove) {
                    grantsDao.deleteGrant(grantToDelete)
                }

                val currentAbilityGrants = allCurrentGrants.filter { it.granted_type == SourceContentEnum.ABILITIES }
                val currentGrantedAbilityIds = currentAbilityGrants.map { it.granted_content_id }.toSet()

                for (domainAbility in character.abilities) {
                    abilitiesDao.insertAbility(domainAbility.toAbilityEntity())

                    if (!currentGrantedAbilityIds.contains(domainAbility.id)) {
                        grantsDao.insertGrant(GrantsEntity(
                            id = domainAbility.grantId ?: UUID.randomUUID().toString(),
                            granter_type_enum = SourceContentEnum.CREATURES,
                            granter_content_id = character.id,
                            granted_type = SourceContentEnum.ABILITIES,
                            granted_content_id = domainAbility.id
                        ))
                    }
                }

                val domainAbilityContentIds = character.abilities.map { it.id }.toSet()
                val abilitiesToRemove = currentAbilityGrants.filter { it.granted_content_id !in domainAbilityContentIds }
                for (grantToDelete in abilitiesToRemove) {
                    grantsDao.deleteGrant(grantToDelete)
                }

                Resource.Success(Unit)
            } catch (e: Exception) {
                return@withContext (Resource.Error("Error saving character: ${e.localizedMessage ?: "Unknown error"}"))
            }
        }
    }

    override suspend fun buildCharacter(character: ActorCreationContext, authorId: String): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {

                val content = RetrofitInstance.contentService.createContent(
                    request = ContentCreateRequest(
                        sourceContentEnum = SourceContentEnum.CREATURES,
                        visibilityEnum = VisibilityEnum.PUBLIC,
                        authorId = authorId
                    )
                )

                if (content.isSuccessful) {
                    val creature = RetrofitInstance.creatureService.createCreature(
                        request = CreatureCreateRequest(
                            contentId = content.body()?.id ?: "",
                            name = character.name ?: "",
                            sizeEnum = getCreatureSizeForRaceById(character.race ?: 1),
                            typeEnum = CreatureTypeEnum.HUMANOID,
                            alignmentEnum = getAlignmentEnumById(character.alignment ?: 1),
                            baseHp = 15,
                            baseAc = 10,
                            creatureSourceType = CreatureSourceType.CHARACTER
                        )
                    )
                    if (creature.isSuccessful){
                        val myCharacter = characterService.createCharacter(
                            request = CharacterCreateRequest(
                                creatureId = creature.body()?.id ?: "",
                                race = character.race?: 1,
                                characterClass = character.characterClass ?: 1,
                                name = character.name ?: "",
                                alignment = character.alignment?: 1,
                                age = character.age,
                                ideals = character.ideals,
                                personality = character.personality,
                                flaws = character.flaws,
                                biography = character.biography,
                                appearance = character.appearance,
                                height = character.height,
                                weight = character.weight,
                                skinColor = character.skinColor,
                                hairColor = character.hairColor,
                                faith = character.faith,
                                eyeColor = character.eyeColor,
                                gender = character.gender
                            )
                        )
                        if(myCharacter.isSuccessful){
                            val classGrant = RetrofitInstance.grantService.createGrant(
                                request = GrantCreateRequest(
                                    granterType = SourceContentEnum.CREATURES,
                                    granterContentId = myCharacter.body()?.id ?: "",
                                    grantedType = SourceContentEnum.CLASS,
                                    grantedContentId = getApiClassId(character.characterClass?:1)
                                )
                            )
                            val characterContentId = content.body()?.id?:""
                            val abilitiesValues = getTypicalAbilityScores(character.characterClass?:100)

                            // Strength
                            createAbilityAndSkills(
                                AbilityTypeEnum.STRENGTH,
                                abilitiesValues,
                                authorId,
                                characterContentId,
                                skillToAbilityMap
                            )

                            // Dexterity
                            createAbilityAndSkills(
                                AbilityTypeEnum.DEXTERITY,
                                abilitiesValues,
                                authorId,
                                characterContentId,
                                skillToAbilityMap
                            )

                            // Constitution
                            createAbilityAndSkills(
                                AbilityTypeEnum.CONSTITUTION,
                                abilitiesValues,
                                authorId,
                                characterContentId,
                                skillToAbilityMap
                            )

                            // Intelligence
                            createAbilityAndSkills(
                                AbilityTypeEnum.INTELLIGENCE,
                                abilitiesValues,
                                authorId,
                                characterContentId,
                                skillToAbilityMap
                            )

                            // Wisdom
                            createAbilityAndSkills(
                                AbilityTypeEnum.WISDOM,
                                abilitiesValues,
                                authorId,
                                characterContentId,
                                skillToAbilityMap
                            )

                            // Charisma
                            createAbilityAndSkills(
                                AbilityTypeEnum.CHARISMA,
                                abilitiesValues,
                                authorId,
                                characterContentId,
                                skillToAbilityMap
                            )

                            val background = RetrofitInstance.backgroundService.getAllBackgrounds().firstOrNull()
                                val backgroundGrant = RetrofitInstance.grantService.createGrant(
                                    GrantCreateRequest(
                                        granterType = SourceContentEnum.CREATURES,
                                        granterContentId = characterContentId,
                                        grantedType = SourceContentEnum.BACKGROUND,
                                        grantedContentId = background?.id ?: ""
                                    )
                                )
                        }
                    }
                }
                Resource.Success(Unit)
            } catch (e: Exception){
                Resource.Error("Error building character")
            }
        }
    }
}

fun getCreatureSizeForRaceById(raceId: Int): CreatureSizeEnum {
    return when (raceId) {
        1 -> CreatureSizeEnum.MEDIUM // Human
        2 -> CreatureSizeEnum.MEDIUM // Elf
        3 -> CreatureSizeEnum.MEDIUM // Dwarf
        4 -> CreatureSizeEnum.SMALL   // Halfling
        5 -> CreatureSizeEnum.MEDIUM // Dragonborn
        6 -> CreatureSizeEnum.SMALL   // Gnome
        7 -> CreatureSizeEnum.MEDIUM // Half-elf
        8 -> CreatureSizeEnum.MEDIUM // Half-orc
        9 -> CreatureSizeEnum.MEDIUM // Tiefling
        else -> CreatureSizeEnum.MEDIUM
    }
}

fun getAlignmentEnumById(alignmentId: Int): AlignmentEnum {
    return when (alignmentId) {
        1 -> AlignmentEnum.LAWFUL_GOOD
        2 -> AlignmentEnum.NEUTRAL_GOOD
        3 -> AlignmentEnum.CHAOTIC_GOOD
        4 -> AlignmentEnum.LAWFUL_NEUTRAL
        5 -> AlignmentEnum.TRUE_NEUTRAL
        6 -> AlignmentEnum.CHAOTIC_NEUTRAL
        7 -> AlignmentEnum.LAWFUL_EVIL
        8 -> AlignmentEnum.NEUTRAL_EVIL
        9 -> AlignmentEnum.CHAOTIC_EVIL
        else -> AlignmentEnum.TRUE_NEUTRAL
    }
}

fun getApiClassId(localClassId: Int): String {
    return when (localClassId) {
        1 -> "565ea329-1146-4064-a179-35554b744370" // Barbarian
        2 -> "6833332a-964a-4cc6-b6d8-ee51f1461339" // Bard
        3 -> "6f5f4700-c159-4a70-86c5-8b60e785bce8" // Cleric
        4 -> "bdf292cf-a542-434a-98e5-2d93d6a8478f" // Druid
        5 -> "ec38825a-5367-4a28-b578-21dd0e93a7f6" // Fighter
        6 -> "8718b449-7f71-4d3a-abfd-9c6aabbc5e13" // Monk
        7 -> "44f1d62f-4310-4063-a65c-a1c8533ed731" // Paladin
        8 -> "97b308b0-e5f2-42ef-b47f-774a1ce4a1d9" // Ranger
        9 -> "71df9e15-822a-4e28-93cc-5fd0af38957a" // Rogue
        10 -> "41b30039-96be-4b40-9173-9df57722a26f" // Sorcerer
        11 -> "c1c94b50-5dec-48c1-a7f2-7f3e7d4b94f1" // Warlock
        12 -> "19d0f471-849a-43df-ae69-e9d80feb4e4e" // Wizard
        else -> "565ea329-1146-4064-a179-35554b744370"
    }
}
fun getTypicalAbilityScores(localClassId: Int): Map<AbilityTypeEnum, Int> {

    return when (localClassId) {
        1 -> mapOf( // Barbarian: STR, CON, DEX
            AbilityTypeEnum.STRENGTH to 15,
            AbilityTypeEnum.CONSTITUTION to 14,
            AbilityTypeEnum.DEXTERITY to 13,
            AbilityTypeEnum.WISDOM to 12,
            AbilityTypeEnum.CHARISMA to 10,
            AbilityTypeEnum.INTELLIGENCE to 8
        )
        2 -> mapOf( // Bard: CHA, DEX, CON
            AbilityTypeEnum.CHARISMA to 15,
            AbilityTypeEnum.DEXTERITY to 14,
            AbilityTypeEnum.CONSTITUTION to 13,
            AbilityTypeEnum.WISDOM to 12,
            AbilityTypeEnum.INTELLIGENCE to 10,
            AbilityTypeEnum.STRENGTH to 8
        )
        3 -> mapOf( // Cleric: WIS, STR/DEX, CON
            AbilityTypeEnum.WISDOM to 15,
            AbilityTypeEnum.STRENGTH to 14,
            AbilityTypeEnum.CONSTITUTION to 13,
            AbilityTypeEnum.CHARISMA to 12,
            AbilityTypeEnum.INTELLIGENCE to 10,
            AbilityTypeEnum.DEXTERITY to 8
        )
        4 -> mapOf( // Druid: WIS, CON, DEX
            AbilityTypeEnum.WISDOM to 15,
            AbilityTypeEnum.CONSTITUTION to 14,
            AbilityTypeEnum.DEXTERITY to 13,
            AbilityTypeEnum.INTELLIGENCE to 12,
            AbilityTypeEnum.STRENGTH to 10,
            AbilityTypeEnum.CHARISMA to 8
        )
        5 -> mapOf( // Fighter: STR/DEX, CON
            AbilityTypeEnum.STRENGTH to 15,
            AbilityTypeEnum.CONSTITUTION to 14,
            AbilityTypeEnum.DEXTERITY to 13,
            AbilityTypeEnum.WISDOM to 12,
            AbilityTypeEnum.INTELLIGENCE to 10,
            AbilityTypeEnum.CHARISMA to 8
        )
        6 -> mapOf( // Monk: DEX, WIS, CON
            AbilityTypeEnum.DEXTERITY to 15,
            AbilityTypeEnum.WISDOM to 14,
            AbilityTypeEnum.CONSTITUTION to 13,
            AbilityTypeEnum.STRENGTH to 12,
            AbilityTypeEnum.INTELLIGENCE to 10,
            AbilityTypeEnum.CHARISMA to 8
        )
        7 -> mapOf( // Paladin: STR, CHA, CON
            AbilityTypeEnum.STRENGTH to 15,
            AbilityTypeEnum.CHARISMA to 14,
            AbilityTypeEnum.CONSTITUTION to 13,
            AbilityTypeEnum.WISDOM to 12,
            AbilityTypeEnum.DEXTERITY to 10,
            AbilityTypeEnum.INTELLIGENCE to 8
        )
        8 -> mapOf( // Ranger: DEX, WIS, CON
            AbilityTypeEnum.DEXTERITY to 15,
            AbilityTypeEnum.WISDOM to 14,
            AbilityTypeEnum.CONSTITUTION to 13,
            AbilityTypeEnum.STRENGTH to 12,
            AbilityTypeEnum.CHARISMA to 10,
            AbilityTypeEnum.INTELLIGENCE to 8
        )
        9 -> mapOf( // Rogue: DEX, INT/CON
            AbilityTypeEnum.DEXTERITY to 15,
            AbilityTypeEnum.INTELLIGENCE to 14,
            AbilityTypeEnum.CONSTITUTION to 13,
            AbilityTypeEnum.CHARISMA to 12,
            AbilityTypeEnum.WISDOM to 10,
            AbilityTypeEnum.STRENGTH to 8
        )
        10 -> mapOf( // Sorcerer: CHA, CON, DEX
            AbilityTypeEnum.CHARISMA to 15,
            AbilityTypeEnum.CONSTITUTION to 14,
            AbilityTypeEnum.DEXTERITY to 13,
            AbilityTypeEnum.WISDOM to 12,
            AbilityTypeEnum.INTELLIGENCE to 10,
            AbilityTypeEnum.STRENGTH to 8
        )
        11 -> mapOf( // Warlock: CHA, CON, DEX
            AbilityTypeEnum.CHARISMA to 15,
            AbilityTypeEnum.CONSTITUTION to 14,
            AbilityTypeEnum.DEXTERITY to 13,
            AbilityTypeEnum.WISDOM to 12,
            AbilityTypeEnum.INTELLIGENCE to 10,
            AbilityTypeEnum.STRENGTH to 8
        )
        12 -> mapOf( // Wizard: INT, CON, DEX
            AbilityTypeEnum.INTELLIGENCE to 15,
            AbilityTypeEnum.CONSTITUTION to 14,
            AbilityTypeEnum.DEXTERITY to 13,
            AbilityTypeEnum.WISDOM to 12,
            AbilityTypeEnum.CHARISMA to 10,
            AbilityTypeEnum.STRENGTH to 8
        )
        else -> mapOf( // Default or "Commoner"
            AbilityTypeEnum.STRENGTH to 10,
            AbilityTypeEnum.DEXTERITY to 10,
            AbilityTypeEnum.CONSTITUTION to 10,
            AbilityTypeEnum.INTELLIGENCE to 10,
            AbilityTypeEnum.WISDOM to 10,
            AbilityTypeEnum.CHARISMA to 10
        )
    }
}

fun getAbilityModifier(abilityScore: Int? = 10): Int {
    return (abilityScore?.minus(10))?.div(2) ?: 1
}

suspend fun createAbilityAndSkills(
    abilityType: AbilityTypeEnum,
    abilitiesValues: Map<AbilityTypeEnum, Int>,
    authorId: String,
    characterContentId: String,
    skillMappings: Map<SkillTypeEnum, AbilityTypeEnum>
) {
    val abilityScore = abilitiesValues[abilityType] ?: 10
    val abilityModifier = getAbilityModifier(abilityScore)

    val abilityContentResponse = RetrofitInstance.contentService.createContent(
        ContentCreateRequest(
            sourceContentEnum = SourceContentEnum.ABILITIES,
            visibilityEnum = VisibilityEnum.PUBLIC,
            authorId = authorId
        )
    )

    if (abilityContentResponse.isSuccessful) {
        val abilityContentId = abilityContentResponse.body()?.id ?: return

        val abilityResponse = RetrofitInstance.abilityService.createAbility(
            AbilityCreateRequest(
                contentId = abilityContentId,
                abilityTypeEnum = abilityType,
                modifier = abilityModifier,
                value = abilityScore
            )
        )

        if (abilityResponse.isSuccessful) {

            skillMappings.filterValues { it == abilityType }.keys.forEach { skillType ->
                RetrofitInstance.skillService.createSkill(
                    SkillCreateRequest(
                        skillTypeEnum = skillType,
                        proficiencyLevelEnum = ProficiencyLevelEnum.NOT_PROFICIENT,
                        abilityId = abilityContentId
                    )
                )
            }

            RetrofitInstance.grantService.createGrant(
                GrantCreateRequest(
                    granterType = SourceContentEnum.CREATURES,
                    granterContentId = characterContentId,
                    grantedType = SourceContentEnum.ABILITIES,
                    grantedContentId = abilityContentId
                )
            )
        }
    }
}
val skillToAbilityMap = mapOf(
    SkillTypeEnum.ATHLETICS to AbilityTypeEnum.STRENGTH,

    SkillTypeEnum.ACROBATICS to AbilityTypeEnum.DEXTERITY,
    SkillTypeEnum.SLEIGHT_OF_HAND to AbilityTypeEnum.DEXTERITY,
    SkillTypeEnum.STEALTH to AbilityTypeEnum.DEXTERITY,

    SkillTypeEnum.ARCANA to AbilityTypeEnum.INTELLIGENCE,
    SkillTypeEnum.HISTORY to AbilityTypeEnum.INTELLIGENCE,
    SkillTypeEnum.INVESTIGATION to AbilityTypeEnum.INTELLIGENCE,
    SkillTypeEnum.NATURE to AbilityTypeEnum.INTELLIGENCE,
    SkillTypeEnum.RELIGION to AbilityTypeEnum.INTELLIGENCE,

    SkillTypeEnum.ANIMAL_HANDLING to AbilityTypeEnum.WISDOM,
    SkillTypeEnum.INSIGHT to AbilityTypeEnum.WISDOM,
    SkillTypeEnum.MEDICINE to AbilityTypeEnum.WISDOM,
    SkillTypeEnum.PERCEPTION to AbilityTypeEnum.WISDOM,
    SkillTypeEnum.SURVIVAL to AbilityTypeEnum.WISDOM,

    SkillTypeEnum.DECEPTION to AbilityTypeEnum.CHARISMA,
    SkillTypeEnum.INTIMIDATION to AbilityTypeEnum.CHARISMA,
    SkillTypeEnum.PERFORMANCE to AbilityTypeEnum.CHARISMA,
    SkillTypeEnum.PERSUASION to AbilityTypeEnum.CHARISMA
)
