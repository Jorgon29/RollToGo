package com.terraplanistas.rolltogo.data.repository.playstyleRepository

import android.content.Context
import com.terraplanistas.rolltogo.data.model.Playstyle
import kotlinx.coroutines.flow.Flow

interface PlaystyleRepository {
    fun getPlaystyles(): List<Playstyle>
}