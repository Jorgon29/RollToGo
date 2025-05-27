package com.terraplanistas.rolltogo.data.database.repository.playstyleRepository

import android.content.Context
import com.terraplanistas.rolltogo.data.model.Playstyle
import kotlinx.coroutines.flow.Flow

interface PlaystyleRepository {
    fun getPlaystyles(): List<Playstyle>

    fun addPlaystyle(playstyle: Playstyle)
}