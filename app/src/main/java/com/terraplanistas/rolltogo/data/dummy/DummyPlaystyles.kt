package com.terraplanistas.rolltogo.data.dummy

import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Scale
import com.composables.icons.lucide.ShieldPlus
import com.composables.icons.lucide.Sparkles
import com.composables.icons.lucide.Spline
import com.composables.icons.lucide.Swords
import com.terraplanistas.rolltogo.data.model.Playstyle

val dummyPlaystyles = listOf<Playstyle>(
    Playstyle(
        id = 1,
        title = "melee",
        icon = Lucide.Swords,
        description = "Luchar en combate cuerpo a cuerpo",
    ),
    Playstyle(
        id = 2,
        title = "magic",
        icon = Lucide.Sparkles,
        description = "Usar magia y poderosa",
    ),
    Playstyle(
        id = 3,
        title = "support",
        icon = Lucide.ShieldPlus,
        description = "Apoyar y proteger a tus aliados",
    ),
    Playstyle(
        id = 4,
        title = "versatile",
        icon = Lucide.Spline,
        description = "Ser táctico, versátil o sigiloso",
    ),
    Playstyle(
        id = 5,
        title = "balanced",
        icon = Lucide.Scale,
        description = "Usar magia y poderosa",
    ),

)