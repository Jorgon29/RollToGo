package com.terraplanistas.rolltogo.data.database.repository.playstyleRepository

import android.content.Context
import com.terraplanistas.rolltogo.data.defaults.getDefaultPlastyles
import com.terraplanistas.rolltogo.data.model.Playstyle

class PlaystyleRepositoryImplementation(
    private val context: Context
) : PlaystyleRepository {

    override fun getPlaystyles(): List<Playstyle> {
        return getDefaultPlastyles(context)
    }
}