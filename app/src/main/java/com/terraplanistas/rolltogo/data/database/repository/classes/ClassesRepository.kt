package com.terraplanistas.rolltogo.data.database.repository.classes

import android.content.Context
import com.terraplanistas.rolltogo.data.defaults.getDefaultCharacterClasses
import com.terraplanistas.rolltogo.data.model.CharacterClass

interface ClassesRepository {
    fun getClasses(): List<CharacterClass>
}