package com.terraplanistas.rolltogo.data.database.repository.characters

import com.terraplanistas.rolltogo.data.database.dao.classDao.ClassDao
import com.terraplanistas.rolltogo.data.database.dao.classDao.SpellcastingDao
import com.terraplanistas.rolltogo.data.database.dao.creatures.CharactersDao
import com.terraplanistas.rolltogo.data.database.dao.creatures.CreaturesDao
import com.terraplanistas.rolltogo.data.database.dao.grants.GrantsDao
import com.terraplanistas.rolltogo.data.database.dao.items.ItemDao
import com.terraplanistas.rolltogo.data.database.dao.items.ItemTagDao
import com.terraplanistas.rolltogo.data.database.dao.misc.BackgroundDao
import com.terraplanistas.rolltogo.data.database.dao.misc.FeatsDao
import com.terraplanistas.rolltogo.data.database.dao.misc.SkillDao
import com.terraplanistas.rolltogo.data.database.dao.species.SpeciesDao
import com.terraplanistas.rolltogo.data.database.dao.spells.SpellDao
import com.terraplanistas.rolltogo.data.database.dao.spells.SpellMaterialDao
import com.terraplanistas.rolltogo.data.database.entities.creatures.CharactersEntity
import com.terraplanistas.rolltogo.data.database.entities.items.toDomainItem
import com.terraplanistas.rolltogo.data.database.entities.misc.toDomainFeats
import com.terraplanistas.rolltogo.data.database.entities.misc.toDomainSkill
import com.terraplanistas.rolltogo.data.database.entities.species.toDomainRace
import com.terraplanistas.rolltogo.data.database.entities.spells.toDomainSpell
import com.terraplanistas.rolltogo.data.enums.AbilityEnum
import com.terraplanistas.rolltogo.data.enums.SizeEnum
import com.terraplanistas.rolltogo.data.model.character.DomainCharacter
import com.terraplanistas.rolltogo.data.model.character.DomainFeats
import com.terraplanistas.rolltogo.data.model.character.DomainItem
import com.terraplanistas.rolltogo.data.model.character.DomainRace
import com.terraplanistas.rolltogo.data.model.character.DomainSkill
import com.terraplanistas.rolltogo.data.model.character.DomainSpell
import com.terraplanistas.rolltogo.helpers.Resource
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
    private val backgroundDao: BackgroundDao
) : CharacterRepository {

    private suspend fun buildDomainCharacter(charEntity: CharactersEntity): DomainCharacter? {
        val creatureEntity = creaturesDao.getCreatureById(charEntity.id).firstOrNull()
            ?: return null

        val raceEntity = speciesDao.getSpeciesById(charEntity.race_id).firstOrNull()
        val domainRace = raceEntity?.toDomainRace() ?: DomainRace("", "Unknown Race", "", "", SizeEnum.MEDIUM)

        val backgroundGrant = grantsDao.getGrantsByGranterContentId(charEntity.id)
            .firstOrNull()
            ?.find { it.granted_type == "BACKGROUND" }
        val backgroundEntity = backgroundGrant?.let {
            backgroundDao.getBackgroundById(it.granted_content_id).firstOrNull()
        }
        val backgroundTitle = backgroundEntity?.name ?: "N/A"
        val backgroundDescription = backgroundEntity?.description ?: "N/A"

        var characterClass = "Unknown"
        var spellcastingAbility: AbilityEnum? = null

        val classGrant = grantsDao.getGrantsByGranterContentId(charEntity.id)
            .firstOrNull()
            ?.find { it.granted_type == "CLASS" }
        classGrant?.let {
            val classEntity = classDao.getClassById(it.granted_content_id).firstOrNull()
            characterClass = classEntity?.name ?: "Unknown"

            val spellcastingEntity = spellcastingDao.getSpellcastingByClassId(it.granted_content_id).firstOrNull()

        }

        val domainSkills = mutableListOf<DomainSkill>()
        val skillGrants = grantsDao.getGrantsByGranterContentId(charEntity.id)
            .firstOrNull()
            ?.filter { it.granted_type == "SKILL_PROFICIENCY" }
            ?: emptyList()

        for (skillGrant in skillGrants) {
            val skillEntity = skillDao.getSkillById(skillGrant.granted_content_id).firstOrNull()
            skillEntity?.let {
                domainSkills.add(it.toDomainSkill())
            }
        }

        val domainItems = mutableListOf<DomainItem>()
        val itemGrants = grantsDao.getGrantsByGranterContentId(charEntity.id)
            .firstOrNull()
            ?.filter { it.granted_type == "ITEM" } // Ajusta el tipo
            ?: emptyList()

        for (itemGrant in itemGrants) {
            val itemEntity = itemDao.getItemById(itemGrant.granted_content_id).firstOrNull()
            itemEntity?.let {
                val tags = itemTagDao.getTagsByItemId(it.id).firstOrNull()
                val tagStrings = tags?.map { it.tag } ?: emptyList()
                domainItems.add(it.toDomainItem(tagStrings))
            }
        }

        val domainFeats = mutableListOf<DomainFeats>()
        val featGrants = grantsDao.getGrantsByGranterContentId(charEntity.id)
            .firstOrNull()
            ?.filter { it.granted_type == "FEAT" }
            ?: emptyList()
        for (featGrant in featGrants) {
            val featEntity = featsDao.getFeatById(featGrant.granted_content_id).firstOrNull()
            featEntity?.let {
                domainFeats.add(it.toDomainFeats())
            }
        }

        val domainSpells = mutableListOf<DomainSpell>()
        val spellGrants = grantsDao.getGrantsByGranterContentId(charEntity.id)
            .firstOrNull()
            ?.filter { it.granted_type == "SPELL" }
            ?: emptyList()
        for (spellGrant in spellGrants) {
            val spellEntity = spellDao.getSpellById(spellGrant.granted_content_id).firstOrNull()
            spellEntity?.let { spell ->
                val materialEntities = spellMaterialDao.getMaterialsForSpell(spell.id).firstOrNull() ?: emptyList()
                val spellMaterialItems = mutableListOf<DomainItem>()
                for (mat in materialEntities) {
                    val materialItemEntity = itemDao.getItemById(mat.item_id).firstOrNull()
                    materialItemEntity?.let {
                        spellMaterialItems.add(it.toDomainItem(emptyList()))
                    }
                }
                domainSpells.add(spell.toDomainSpell(spellMaterialItems))
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
            spellcastingAbility = spellcastingAbility ?: AbilityEnum.NONE,
            skills = domainSkills,
            items = domainItems,
            feats = domainFeats,
            spells = domainSpells
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

                Resource.Success(Unit)
            } catch (e: Exception) {
                Resource.Error("Error deleting character: ${e.localizedMessage ?: "Unknown error"}")
            }
        }
    }
}