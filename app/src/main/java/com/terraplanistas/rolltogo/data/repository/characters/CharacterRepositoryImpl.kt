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
import com.terraplanistas.rolltogo.data.database.entities.misc.SkillEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.toDomainAbility
import com.terraplanistas.rolltogo.data.database.entities.misc.toDomainFeats
import com.terraplanistas.rolltogo.data.database.entities.misc.toDomainSkill
import com.terraplanistas.rolltogo.data.database.entities.species.toDomainRace
import com.terraplanistas.rolltogo.data.database.entities.spells.SpellMaterialEntity
import com.terraplanistas.rolltogo.data.database.entities.spells.toDomainSpell
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.CreatureSizeEnum
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
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
import com.terraplanistas.rolltogo.helpers.Resource
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
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
}
