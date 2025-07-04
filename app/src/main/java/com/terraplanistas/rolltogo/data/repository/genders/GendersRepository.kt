package com.terraplanistas.rolltogo.data.repository.genders

import android.content.Context
import com.terraplanistas.rolltogo.data.model.CharacterGender

interface GendersRepository {
    fun getGenders(): List<CharacterGender>
}