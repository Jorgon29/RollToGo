package com.terraplanistas.rolltogo.data.defaults

import android.content.Context
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Scale
import com.composables.icons.lucide.ShieldPlus
import com.composables.icons.lucide.Sparkles
import com.composables.icons.lucide.Spline
import com.composables.icons.lucide.Swords
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.data.model.Playstyle

fun getDefaultPlastyles( context: Context): List<Playstyle> {
    return listOf<Playstyle>(
        Playstyle(
            id = 1,
            title = context.getString(R.string.actor_creation_playstyle_melee),
            icon = Lucide.Swords,
            description = context.getString(R.string.actor_creation_playstyle_melee_description)
        ),
        Playstyle(
            id = 2,
            title = context.getString(R.string.actor_creation_playstyle_magic),
            icon = Lucide.Sparkles,
            description = context.getString(R.string.actor_creation_playstyle_magic_description),
        ),
        Playstyle(
            id = 3,
            title = context.getString(R.string.actor_creation_playstyle_support),
            icon = Lucide.ShieldPlus,
            description = context.getString(R.string.actor_creation_playstyle_support_description),
        ),
        Playstyle(
            id = 4,
            title = context.getString(R.string.actor_creation_playstyle_versatile),
            icon = Lucide.Spline,
            description = context.getString(R.string.actor_creation_playstyle_versatile_description),
        ),
        Playstyle(
            id = 5,
            title = context.getString(R.string.actor_creation_playstyle_balanced),
            icon = Lucide.Scale,
            description = context.getString(R.string.actor_creation_playstyle_balanced_description),
        ),

        )
}
