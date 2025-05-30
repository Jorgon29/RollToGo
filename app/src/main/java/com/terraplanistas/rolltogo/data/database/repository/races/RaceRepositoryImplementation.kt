package com.terraplanistas.rolltogo.data.database.repository.races

import android.content.Context
import com.terraplanistas.rolltogo.data.defaults.getDefaultRaces
import com.terraplanistas.rolltogo.data.model.CharacterRace

class RaceRepositoryImplementation(
    private val context: Context
): RaceRepository {
    override fun getRaces(): List<CharacterRace> {
        return getDefaultRaces(context)
    }
}