package com.terraplanistas.rolltogo.data.defaults

import android.content.Context
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.data.model.CharacterAlignment

fun getDefaultAlignments(context: Context): List<CharacterAlignment> {
    return listOf(
        CharacterAlignment(
            id = 1,
            name = context.getString(R.string.actor_creation_alignment_lawful_good)
        ),
        CharacterAlignment(
            id = 2,
            name = context.getString(R.string.actor_creation_alignment_neutral_good)
        ),
        CharacterAlignment(
            id = 3,
            name = context.getString(R.string.actor_creation_alignment_chaotic_good)
        ),
        CharacterAlignment(
            id = 4,
            name = context.getString(R.string.actor_creation_alignment_lawful_neutral)
        ),
        CharacterAlignment(
            id = 5,
            name = context.getString(R.string.actor_creation_alignment_true_neutral)
        ),
        CharacterAlignment(
            id = 6,
            name = context.getString(R.string.actor_creation_alignment_chaotic_neutral)
        ),
        CharacterAlignment(
            id = 7,
            name = context.getString(R.string.actor_creation_alignment_lawful_evil)
        ),
        CharacterAlignment(
            id = 8,
            name = context.getString(R.string.actor_creation_alignment_neutral_evil)
        ),
        CharacterAlignment(
            id = 9,
            name = context.getString(R.string.actor_creation_alignment_chaotic_evil)
        )
    )
}
