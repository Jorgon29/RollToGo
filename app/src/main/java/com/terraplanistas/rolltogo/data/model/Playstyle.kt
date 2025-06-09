package com.terraplanistas.rolltogo.data.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.composables.icons.lucide.CircleHelp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Scale
import com.composables.icons.lucide.ShieldPlus
import com.composables.icons.lucide.Sparkles
import com.composables.icons.lucide.Spline
import com.composables.icons.lucide.Swords

data class Playstyle(
    val id: Int,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val recommendedClasses: List<Int>? = null
)

fun iconNameFor(icon: ImageVector): String = when (icon) {
    Lucide.Swords -> "swords"
    Lucide.Sparkles -> "sparkles"
    Lucide.ShieldPlus -> "shield_plus"
    Lucide.Spline -> "spline"
    Lucide.Scale -> "scale"
    else -> "help_circle"
}

