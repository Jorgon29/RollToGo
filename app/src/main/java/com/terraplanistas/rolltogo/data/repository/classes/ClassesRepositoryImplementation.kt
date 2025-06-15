package com.terraplanistas.rolltogo.data.repository.classes

import android.content.Context
import com.terraplanistas.rolltogo.data.defaults.getDefaultCharacterClasses
import com.terraplanistas.rolltogo.data.model.CharacterClass

class ClassesRepositoryImplementation(
    private val context: Context
): ClassesRepository {
    override fun getClasses(): List<CharacterClass> {
        return getDefaultCharacterClasses(context)
    }
}