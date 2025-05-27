package com.terraplanistas.rolltogo.data.database.repository.playstyleRepository

import android.content.Context
import com.terraplanistas.rolltogo.data.database.dao.PlaystyleDao
import com.terraplanistas.rolltogo.data.defaults.getDefaultPlastyles
import com.terraplanistas.rolltogo.data.model.Playstyle
import com.terraplanistas.rolltogo.data.model.toEntity

class PlaystyleRepositoryImplementation(
    private val playstyleDao: PlaystyleDao,
    private val context: Context
) : PlaystyleRepository {

    override fun getPlaystyles(): List<Playstyle> {
        return getDefaultPlastyles(context)
    }

    override fun addPlaystyle(playstyle: Playstyle) {
        playstyleDao.addPlaystyle(playstyle.toEntity())
    }
}