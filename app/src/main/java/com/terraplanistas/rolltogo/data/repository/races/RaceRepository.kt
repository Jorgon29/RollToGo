package com.terraplanistas.rolltogo.data.repository.races

import com.terraplanistas.rolltogo.data.model.CharacterRace

interface RaceRepository {
    fun getRaces(): List<CharacterRace>
}