package com.terraplanistas.rolltogo.data.database.repository.races

import com.terraplanistas.rolltogo.data.model.CharacterRace

interface RaceRepository {
    fun getRaces(): List<CharacterRace>
}