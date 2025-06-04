package com.terraplanistas.rolltogo.data.defaults

import android.content.Context
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.data.model.CharacterGender

fun getDefaultGenders(context: Context): List<CharacterGender>{
    return listOf<CharacterGender>(
        CharacterGender(
            name = context.getString(R.string.gender_masculine),
            id = 1
        ),
        CharacterGender(
            name = context.getString(R.string.gender_feminine),
            id = 2
        ),
        CharacterGender(
            name = context.getString(R.string.gender_other),
            id = 3
        )
    )
}