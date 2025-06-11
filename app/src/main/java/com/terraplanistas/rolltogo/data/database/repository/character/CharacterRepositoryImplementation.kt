package com.terraplanistas.rolltogo.data.database.repository.character

import com.terraplanistas.rolltogo.data.database.dao.CharacterDao
import com.terraplanistas.rolltogo.data.database.entities.toDomain
import com.terraplanistas.rolltogo.data.model.DomainCharacter
import com.terraplanistas.rolltogo.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepositoryImplementation(
    private val characterDao: CharacterDao
): CharactersRepository {
    override fun getCharacters(): Flow<List<DomainCharacter>> {
        return characterDao.getAll().map { it.map { it.toDomain() } }
    }

    override suspend fun searchCharacter(query: String): Flow<List<DomainCharacter>> {
        return characterDao.searchByName(query).map { it.map { it.toDomain() } }
    }

    override suspend fun addCharacter(character: DomainCharacter) {
        characterDao.insert(character.toEntity())
    }

    override suspend fun addCharacters(characters: List<DomainCharacter>) {
        characterDao.insertAll(characters.map { it.toEntity() })
    }

    override suspend fun removeCharacter(character: DomainCharacter) {
        characterDao.delete(character.toEntity())
    }

    override fun getCharacterById(id: Int): Flow<DomainCharacter>? {
        return characterDao.getById(id)?.map { it.toDomain() }
    }
}