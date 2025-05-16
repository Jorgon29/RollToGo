package com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.playstyleStep

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Route
import com.composables.icons.lucide.Scale
import com.composables.icons.lucide.ShieldPlus
import com.composables.icons.lucide.Sparkles
import com.composables.icons.lucide.Swords
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList.SelectionListItem

val playstylesList = listOf(
    SelectionListItem(
        icon = Lucide.Swords,
        name = stringResource(R.string.actor_creation_playstyle_melee),
        id = 0
    ),
    SelectionListItem(
        icon = Lucide.Sparkles,
        name = stringResource(R.string.actor_creation_playstyle_magic),
        id = 1
    ),
    SelectionListItem(
        icon = Lucide.ShieldPlus,
        name = stringResource(R.string.actor_creation_playstyle_support),
        id = 2
    ),
    SelectionListItem(
        icon = Lucide.Route,
        name = stringResource(R.string.actor_creation_playstyle_versatile),
        id = 3
    ),
    SelectionListItem(
        icon = Lucide.Scale,
        name = stringResource(R.string.actor_creation_playstyle_balanced),
        id = 3
    )
)