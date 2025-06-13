package com.terraplanistas.rolltogo.data.database.repository.characters

import com.terraplanistas.rolltogo.data.model.character.DomainCharacter
import com.terraplanistas.rolltogo.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacterById(id: String): Flow<Resource<DomainCharacter>>
    fun searchCharacters(query: String): Flow<Resource<List<DomainCharacter>>>?
    suspend fun deleteCharacter(characterId: String): Resource<Unit>
}