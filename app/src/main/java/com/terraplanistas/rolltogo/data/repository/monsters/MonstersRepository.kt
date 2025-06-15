package com.terraplanistas.rolltogo.data.repository.monsters

import com.terraplanistas.rolltogo.data.model.creatures.monster.DomainMonster
import com.terraplanistas.rolltogo.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface MonstersRepository {
    fun getMonsterById(id: String): Flow<Resource<DomainMonster>>
    fun searchMonsters(query: String): Flow<Resource<List<DomainMonster>>>?
    suspend fun deleteMonster(characterId: String): Resource<Unit>
    suspend fun saveMonster(character: DomainMonster): Resource<Unit>
}