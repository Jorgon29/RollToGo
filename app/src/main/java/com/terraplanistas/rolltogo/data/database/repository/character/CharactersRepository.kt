package com.terraplanistas.rolltogo.data.database.repository.character

import com.terraplanistas.rolltogo.data.model.DomainCharacter
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getCharacters(): Flow<List<DomainCharacter>>

    suspend fun searchCharacter(query: String): Flow<List<DomainCharacter>>

    suspend fun addCharacter(character: DomainCharacter)

    suspend fun addCharacters(characters: List<DomainCharacter>)
    suspend fun removeCharacter(character: DomainCharacter)
    fun getCharacterById(id: Int): Flow<DomainCharacter>?
}