package com.terraplanistas.rolltogo.data.database.repository.playstyleRepository

import com.terraplanistas.rolltogo.data.database.dao.PlaystyleDao
import com.terraplanistas.rolltogo.data.database.entities.PlaystyleEntity
import com.terraplanistas.rolltogo.data.database.entities.toPlaystyle
import com.terraplanistas.rolltogo.data.model.Playstyle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaystyleRepositoryImplementation(
    private val playstyleDao: PlaystyleDao
) : PlaystyleRepository {

    override fun getPlaystyles(): Flow<List<Playstyle>> {
        return playstyleDao.getPlaystyles().map { list ->
            list.map { it.toPlaystyle() }
        }
    }
}