package com.terraplanistas.rolltogo.data.defaults

import android.content.Context
import com.composables.icons.lucide.Cpu
import com.composables.icons.lucide.Dice5
import com.composables.icons.lucide.Feather
import com.composables.icons.lucide.Flame
import com.composables.icons.lucide.Hammer
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.MoonStar
import com.composables.icons.lucide.SquareSplitHorizontal
import com.composables.icons.lucide.Sword
import com.composables.icons.lucide.Users
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.data.model.CharacterRace

fun getDefaultRaces(context: Context): List<CharacterRace>{
    return listOf(
        CharacterRace(
            id = 1,
            name = context.getString(R.string.actor_creation_race_human),
            description = context.getString(R.string.actor_creation_race_human_description),
            icon = Lucide.Users
        ),
        CharacterRace(
            id = 2,
            name = context.getString(R.string.actor_creation_race_elf),
            description = context.getString(R.string.actor_creation_race_elf_description),
            icon = Lucide.Feather
        ),
        CharacterRace(
            id = 3,
            name = context.getString(R.string.actor_creation_race_dwarf),
            description = context.getString(R.string.actor_creation_race_dwarf_description),
            icon = Lucide.Hammer
        ),
        CharacterRace(
            id = 4,
            name = context.getString(R.string.actor_creation_race_halfling),
            description = context.getString(R.string.actor_creation_race_halfling_description),
            icon = Lucide.Dice5
        ),
        CharacterRace(
            id = 5,
            name = context.getString(R.string.actor_creation_race_dragonborn),
            description = context.getString(R.string.actor_creation_race_dragonborn_description),
            icon = Lucide.Flame
        ),
        CharacterRace(
            id = 6,
            name = context.getString(R.string.actor_creation_race_gnome),
            description = context.getString(R.string.actor_creation_race_gnome_description),
            icon = Lucide.Cpu
        ),
        CharacterRace(
            id = 7,
            name = context.getString(R.string.actor_creation_race_halfelf),
            description = context.getString(R.string.actor_creation_race_halfelf_description),
            icon = Lucide.SquareSplitHorizontal
        ),
        CharacterRace(
            id = 8,
            name = context.getString(R.string.actor_creation_race_halforc),
            description = context.getString(R.string.actor_creation_race_halforc_description),
            icon = Lucide.Sword
        ),
        CharacterRace(
            id = 9,
            name = context.getString(R.string.actor_creation_race_tiefling),
            description = context.getString(R.string.actor_creation_race_tiefling_description),
            icon = Lucide.MoonStar
        )
    )
}