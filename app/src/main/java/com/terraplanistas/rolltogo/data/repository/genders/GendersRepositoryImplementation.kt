package com.terraplanistas.rolltogo.data.repository.genders

import android.content.Context
import com.terraplanistas.rolltogo.data.defaults.getDefaultGenders
import com.terraplanistas.rolltogo.data.model.CharacterGender

class GendersRepositoryImplementation(
private val context: Context
): GendersRepository {
    override fun getGenders(): List<CharacterGender>{
        return getDefaultGenders(context)
    }
}