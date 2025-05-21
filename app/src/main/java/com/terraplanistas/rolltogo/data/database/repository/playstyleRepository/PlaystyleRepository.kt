package com.terraplanistas.rolltogo.data.database.repository.playstyleRepository

import com.terraplanistas.rolltogo.data.model.Playstyle
import kotlinx.coroutines.flow.Flow

interface PlaystyleRepository {
    fun getPlaystyles(): Flow<List<Playstyle>>
}