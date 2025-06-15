package com.terraplanistas.rolltogo.data.repository.monsters

// Make sure to add this import if you haven't already in your repository package
import com.terraplanistas.rolltogo.data.database.dao.classDao.SpellcastingDao
import com.terraplanistas.rolltogo.data.database.dao.creatures.CreaturesDao
import com.terraplanistas.rolltogo.data.database.dao.creatures.MonstersDao
import com.terraplanistas.rolltogo.data.database.dao.grants.GrantsDao
import com.terraplanistas.rolltogo.data.database.dao.items.ItemDao
import com.terraplanistas.rolltogo.data.database.dao.items.ItemTagDao
import com.terraplanistas.rolltogo.data.database.dao.misc.FeatsDao
import com.terraplanistas.rolltogo.data.database.dao.misc.SkillDao
import com.terraplanistas.rolltogo.data.database.dao.spells.SpellDao
import com.terraplanistas.rolltogo.data.database.dao.spells.SpellMaterialDao
import com.terraplanistas.rolltogo.data.database.entities.creatures.MonstersEntity
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantsEntity
import com.terraplanistas.rolltogo.data.database.entities.items.toDomainItem
import com.terraplanistas.rolltogo.data.database.entities.misc.toDomainFeats
import com.terraplanistas.rolltogo.data.database.entities.misc.toDomainSkill
import com.terraplanistas.rolltogo.data.database.entities.spells.SpellMaterialEntity
import com.terraplanistas.rolltogo.data.database.entities.spells.toDomainSpell
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainFeats
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainItem
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainSkill
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainSpell
import com.terraplanistas.rolltogo.data.model.creatures.character.toFeatEntity
import com.terraplanistas.rolltogo.data.model.creatures.character.toItemEntity
import com.terraplanistas.rolltogo.data.model.creatures.character.toSkillEntity
import com.terraplanistas.rolltogo.data.model.creatures.character.toSpellEntity
import com.terraplanistas.rolltogo.data.model.creatures.monster.DomainMonster
import com.terraplanistas.rolltogo.data.model.creatures.monster.toCreatureEntity
import com.terraplanistas.rolltogo.data.model.creatures.monster.toMonsterEntity
import com.terraplanistas.rolltogo.helpers.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.util.UUID

class MonsterRepositoryImpl(
    private val monstersDao: MonstersDao,
    private val creaturesDao: CreaturesDao,
    private val grantsDao: GrantsDao,
    private val itemDao: ItemDao,
    private val itemTagDao: ItemTagDao,
    private val featsDao: FeatsDao,
    private val skillDao: SkillDao,
    private val spellDao: SpellDao,
    private val spellcastingDao: SpellcastingDao,
    private val spellMaterialDao: SpellMaterialDao
) : MonstersRepository { // You'll need to define the MonsterRepository interface

    // --- Helper function to build a DomainMonster from entities ---
    private suspend fun buildDomainMonster(monsterEntity: MonstersEntity): DomainMonster? {
        val creatureEntity = creaturesDao.getCreatureById(monsterEntity.id).firstOrNull()
            ?: return null // A monster MUST have a corresponding creature entry

        // Fetch spellcasting ability (assuming it's directly linked or via a grant to the monster's ID)
        // You might need a specific DAO method like getSpellcastingByCreatureId()
        // Or it might be handled via grants just like skills/items.
        // For simplicity, let's assume a direct link via SpellcastingDao to the monster's ID.

        // Skills
        val domainSkills = mutableListOf<DomainSkill>()
        val skillGrants = grantsDao.getGrantsByGranterContentId(monsterEntity.id)
            .firstOrNull()
            ?.filter { it.granted_type == SourceContentEnum.SKILLS }
            ?: emptyList()
        for (skillGrant in skillGrants) {
            val skillEntity = skillDao.getSkillById(skillGrant.granted_content_id).firstOrNull()
            skillEntity?.let {
                domainSkills.add(it.toDomainSkill(grantId = skillGrant.id))
            }
        }

        // Items
        val domainItems = mutableListOf<DomainItem>()
        val itemGrants = grantsDao.getGrantsByGranterContentId(monsterEntity.id)
            .firstOrNull()
            ?.filter { it.granted_type == SourceContentEnum.ITEM }
            ?: emptyList()
        for (itemGrant in itemGrants) {
            val itemEntity = itemDao.getItemById(itemGrant.granted_content_id).firstOrNull()
            itemEntity?.let {
                val tags = itemTagDao.getTagsByItemId(it.id).firstOrNull()
                val tagStrings = tags?.map { it.tag } ?: emptyList()
                domainItems.add(it.toDomainItem(tagStrings, grantId = itemGrant.id))
            }
        }

        // Feats
        val domainFeats = mutableListOf<DomainFeats>()
        val featGrants = grantsDao.getGrantsByGranterContentId(monsterEntity.id)
            .firstOrNull()
            ?.filter { it.granted_type == SourceContentEnum.FEATS }
            ?: emptyList()
        for (featGrant in featGrants) {
            val featEntity = featsDao.getFeatById(featGrant.granted_content_id).firstOrNull()
            featEntity?.let {
                domainFeats.add(it.toDomainFeats(grantId = featGrant.id))
            }
        }

        // Spells
        val domainSpells = mutableListOf<DomainSpell>()
        val spellGrants = grantsDao.getGrantsByGranterContentId(monsterEntity.id)
            .firstOrNull()
            ?.filter { it.granted_type == SourceContentEnum.SPELLS }
            ?: emptyList()
        for (spellGrant in spellGrants) {
            val spellEntity = spellDao.getSpellById(spellGrant.granted_content_id).firstOrNull()
            spellEntity?.let { spell ->
                val materialEntities = spellMaterialDao.getMaterialsForSpell(spell.id).firstOrNull() ?: emptyList()
                // Assuming toDomainSpell handles material entities and also takes grantId
                domainSpells.add(spell.toDomainSpell(materialEntities, itemDao, grantId = spellGrant.id))
            }
        }

        return DomainMonster(
            id = monsterEntity.id,
            name = creatureEntity.name,
            hp = creatureEntity.base_hp,
            ac = creatureEntity.base_ac,
            alignment = creatureEntity.alignment_enum,
            challengeRating = monsterEntity.challenge_rating,
            legendary = monsterEntity.legendary,
            lair = monsterEntity.lair,
            spellcastingAbility = AbilityTypeEnum.ALL,
            skills = domainSkills,
            items = domainItems,
            feats = domainFeats,
            spells = domainSpells,
            size = creatureEntity.size_enum,
            type = creatureEntity.type_enum
        )
    }

    override fun getMonsterById(id: String): Flow<Resource<DomainMonster>> = flow {
        emit(Resource.Loading)
        try {
            val domainMonster = withContext(Dispatchers.IO) {
                monstersDao.getMonsterById(id).firstOrNull()?.let { monsterEntity ->
                    buildDomainMonster(monsterEntity)
                }
            }
            if (domainMonster != null) {
                emit(Resource.Success(domainMonster))
            } else {
                emit(Resource.Error("Monster with ID $id not found."))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error fetching monster: ${e.localizedMessage ?: "Unknown error"}"))
        }
    }

    override fun searchMonsters(query: String): Flow<Resource<List<DomainMonster>>> = flow {
        emit(Resource.Loading)
        try {
            val monsterEntities = withContext(Dispatchers.IO) {
                // Assuming you'll have a search method in CreaturesDao that can be joined with MonstersDao
                // Or you might search in MonstersDao and then fetch creature data.
                // For simplicity, let's assume a joined query in MonstersDao or a combined fetch.
                // If searching by name, you'd search in CreaturesDao and then filter/map.
                creaturesDao.searchCreaturesByName(query).firstOrNull() ?: emptyList()
            }

            val domainMonsters = monsterEntities.mapNotNull { creatureEntity ->
                withContext(Dispatchers.IO) {
                    // Fetch the corresponding monster entity
                    val monsterEntity = monstersDao.getMonsterById(creatureEntity.id).firstOrNull()
                    if (monsterEntity != null) {
                        buildDomainMonster(monsterEntity)
                    } else {
                        null // If creature exists but no monster entity, skip
                    }
                }
            }
            emit(Resource.Success(domainMonsters))
        } catch (e: Exception) {
            emit(Resource.Error("Error searching monsters: ${e.localizedMessage ?: "Unknown error"}"))
        }
    }


    override suspend fun saveMonster(monster: DomainMonster): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                // 1. Save CreatureEntity
                val creatureEntity = monster.toCreatureEntity() // Assuming .toCreatureEntity()
                creaturesDao.insertCreature(creatureEntity)

                // 2. Save MonstersEntity
                val monsterEntity = monster.toMonsterEntity() // Assuming .toMonsterEntity()
                monstersDao.insertMonster(monsterEntity)

                // 3. Manage Grants for skills, items, feats, spells (similar to CharacterRepository)
                val allCurrentGrants = grantsDao.getGrantsByGranterContentId(monster.id)
                    .firstOrNull() ?: emptyList()

                val currentSkillGrants = allCurrentGrants.filter { it.granted_type == SourceContentEnum.SKILLS }
                val currentGrantedSkillContentIds = currentSkillGrants.map { it.granted_content_id }.toSet()

                for (domainSkill in monster.skills) {
                    skillDao.insertSkill(domainSkill.toSkillEntity()) // Upsert the skill itself
                    if (!currentGrantedSkillContentIds.contains(domainSkill.id)) {
                        grantsDao.insertGrant(GrantsEntity(
                            id = domainSkill.grantId ?: UUID.randomUUID().toString(), // Use domain's grantId or generate new
                            granter_type_enum = SourceContentEnum.CREATURES,
                            granter_content_id = monster.id,
                            granted_type = SourceContentEnum.SKILLS,
                            granted_content_id = domainSkill.id
                        ))
                    }
                }
                val domainSkillContentIds = monster.skills.map { it.id }.toSet()
                val skillsToRemove = currentSkillGrants.filter { it.granted_content_id !in domainSkillContentIds }
                for (grantToDelete in skillsToRemove) {
                    grantsDao.deleteGrant(grantToDelete)
                }

                // --- Item Grants Logic ---
                val currentItemGrants = allCurrentGrants.filter { it.granted_type == SourceContentEnum.ITEM }
                val currentGrantedItemContentIds = currentItemGrants.map { it.granted_content_id }.toSet()

                for (domainItem in monster.items) {
                    val itemEntity = domainItem.toItemEntity()
                    itemDao.insertItem(itemEntity)
                    if (!currentGrantedItemContentIds.contains(domainItem.id)) {
                        grantsDao.insertGrant(GrantsEntity(
                            id = domainItem.grantId ?: UUID.randomUUID().toString(),
                            granter_type_enum = SourceContentEnum.CREATURES,
                            granter_content_id = monster.id,
                            granted_type = SourceContentEnum.ITEM,
                            granted_content_id = domainItem.id
                        ))
                    }
                }
                val domainItemContentIds = monster.items.map { it.id }.toSet()
                val itemsToRemove = currentItemGrants.filter { it.granted_content_id !in domainItemContentIds }
                for (grantToDelete in itemsToRemove) {
                    grantsDao.deleteGrant(grantToDelete)
                }

                // --- Feat Grants Logic ---
                val currentFeatGrants = allCurrentGrants.filter { it.granted_type == SourceContentEnum.FEATS }
                val currentGrantedFeatContentIds = currentFeatGrants.map { it.granted_content_id }.toSet()

                for (domainFeat in monster.feats) {
                    featsDao.insertFeat(domainFeat.toFeatEntity())
                    if (!currentGrantedFeatContentIds.contains(domainFeat.id)) {
                        grantsDao.insertGrant(GrantsEntity(
                            id = domainFeat.grantId ?: UUID.randomUUID().toString(),
                            granter_type_enum = SourceContentEnum.CREATURES,
                            granter_content_id = monster.id,
                            granted_type = SourceContentEnum.FEATS,
                            granted_content_id = domainFeat.id
                        ))
                    }
                }
                val domainFeatContentIds = monster.feats.map { it.id }.toSet()
                val featsToRemove = currentFeatGrants.filter { it.granted_content_id !in domainFeatContentIds }
                for (grantToDelete in featsToRemove) {
                    grantsDao.deleteGrant(grantToDelete)
                }

                // --- Spell Grants Logic ---
                val currentSpellGrants = allCurrentGrants.filter { it.granted_type == SourceContentEnum.SPELLS }
                val currentGrantedSpellContentIds = currentSpellGrants.map { it.granted_content_id }.toSet()

                for (domainSpell in monster.spells) {
                    spellDao.insertSpell(domainSpell.toSpellEntity())
                    val currentMaterialEntities = spellMaterialDao.getMaterialsForSpell(domainSpell.id).firstOrNull() ?: emptyList()
                    val newMaterialComponents = domainSpell.spellMaterials

                    val currentMaterialMap = currentMaterialEntities.associateBy { it.item_id }

                    for (newComp in newMaterialComponents) {
                        val existingComp = currentMaterialMap[newComp.item.id]
                        if (existingComp == null || existingComp.consumed != newComp.consumed) {
                            spellMaterialDao.insertSpellMaterial(
                                SpellMaterialEntity(
                                    spell_id = domainSpell.id,
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

                    // Insert grant for the spell itself
                    if (!currentGrantedSpellContentIds.contains(domainSpell.id)) {
                        grantsDao.insertGrant(GrantsEntity(
                            id = domainSpell.grantId ?: UUID.randomUUID().toString(),
                            granter_type_enum = SourceContentEnum.CREATURES,
                            granter_content_id = monster.id,
                            granted_type = SourceContentEnum.SPELLS,
                            granted_content_id = domainSpell.id
                        ))
                    }
                }
                val domainSpellContentIds = monster.spells.map { it.id }.toSet()
                val spellsToRemove = currentSpellGrants.filter { it.granted_content_id !in domainSpellContentIds }
                for (grantToDelete in spellsToRemove) {
                    grantsDao.deleteGrant(grantToDelete)
                }

                Resource.Success(Unit)
            } catch (e: Exception) {
                // Corrected: Return Resource.Error directly for suspend fun
                return@withContext Resource.Error("Error saving monster: ${e.localizedMessage ?: "Unknown error"}")
            }
        }
    }


    override suspend fun deleteMonster(monsterId: String): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val monsterExists = monstersDao.getMonsterById(monsterId).firstOrNull() != null
                if (!monsterExists) {
                    return@withContext Resource.Error("Monster with ID $monsterId not found.")
                }
                monstersDao.deleteMonsterById(monsterId)

                val grantsToDelete = grantsDao.getGrantsByGranterContentId(monsterId).firstOrNull() ?: emptyList()
                for(grant in grantsToDelete) {
                    grantsDao.deleteGrant(grant)
                }

                Resource.Success(Unit)
            } catch (e: Exception) {
                Resource.Error("Error deleting monster: ${e.localizedMessage ?: "Unknown error"}")
            }
        }
    }
}