package com.terraplanistas.rolltogo.data.defaults

import android.content.Context
import com.composables.icons.lucide.Axe
import com.composables.icons.lucide.Bird
import com.composables.icons.lucide.BookOpen
import com.composables.icons.lucide.Cross
import com.composables.icons.lucide.Hand
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Moon
import com.composables.icons.lucide.Music
import com.composables.icons.lucide.PocketKnife
import com.composables.icons.lucide.Shield
import com.composables.icons.lucide.Sparkles
import com.composables.icons.lucide.Swords
import com.composables.icons.lucide.TreePine
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.data.model.CharacterClass

fun getDefaultCharacterClasses(context: Context): List<CharacterClass> {
    return listOf(
        CharacterClass(
            id = 1,
            name = context.getString(R.string.actor_creation_class_barbarian),
            description = context.getString(R.string.actor_creation_class_barbarian_description),
            icon = Lucide.Axe
        ),
        CharacterClass(
            id = 2,
            name = context.getString(R.string.actor_creation_class_bard),
            description = context.getString(R.string.actor_creation_class_bard_description),
            icon = Lucide.Music
        ),
        CharacterClass(
            id = 3,
            name = context.getString(R.string.actor_creation_class_cleric),
            description = context.getString(R.string.actor_creation_class_cleric_description),
            icon = Lucide.Cross
        ),
        CharacterClass(
            id = 4,
            name = context.getString(R.string.actor_creation_class_druid),
            description = context.getString(R.string.actor_creation_class_druid_description),
            icon = Lucide.Bird
        ),
        CharacterClass(
            id = 5,
            name = context.getString(R.string.actor_creation_class_fighter),
            description = context.getString(R.string.actor_creation_class_fighter_description),
            icon = Lucide.Swords
        ),
        CharacterClass(
            id = 6,
            name = context.getString(R.string.actor_creation_class_monk),
            description = context.getString(R.string.actor_creation_class_monk_description),
            icon = Lucide.Hand
        ),
        CharacterClass(
            id = 7,
            name = context.getString(R.string.actor_creation_class_paladin),
            description = context.getString(R.string.actor_creation_class_paladin_description),
            icon = Lucide.Shield
        ),
        CharacterClass(
            id = 8,
            name = context.getString(R.string.actor_creation_class_ranger),
            description = context.getString(R.string.actor_creation_class_ranger_description),
            icon = Lucide.TreePine
        ),
        CharacterClass(
            id = 9,
            name = context.getString(R.string.actor_creation_class_rogue),
            description = context.getString(R.string.actor_creation_class_rogue_description),
            icon = Lucide.PocketKnife
        ),
        CharacterClass(
            id = 10,
            name = context.getString(R.string.actor_creation_class_sorcerer),
            description = context.getString(R.string.actor_creation_class_sorcerer_description),
            icon = Lucide.Sparkles
        ),
        CharacterClass(
            id = 11,
            name = context.getString(R.string.actor_creation_class_warlock),
            description = context.getString(R.string.actor_creation_class_warlock_description),
            icon = Lucide.Moon
        ),
        CharacterClass(
            id = 12,
            name = context.getString(R.string.actor_creation_class_wizard),
            description = context.getString(R.string.actor_creation_class_wizard_description),
            icon = Lucide.BookOpen
        )
    )
}
