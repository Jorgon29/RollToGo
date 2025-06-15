package com.terraplanistas.rolltogo.data.repository.characters

import com.terraplanistas.rolltogo.data.model.creatures.character.DomainCharacter
import com.terraplanistas.rolltogo.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacterById(id: String): Flow<Resource<DomainCharacter>>
    fun searchCharacters(query: String): Flow<Resource<List<DomainCharacter>>>?
    suspend fun deleteCharacter(characterId: String): Resource<Unit>
    suspend fun saveCharacter(character: DomainCharacter): Resource<Unit>
}