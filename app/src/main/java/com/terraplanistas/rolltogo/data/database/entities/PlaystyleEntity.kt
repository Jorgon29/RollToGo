package com.terraplanistas.rolltogo.data.database.entities

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.composables.icons.lucide.CircleHelp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Scale
import com.composables.icons.lucide.ShieldPlus
import com.composables.icons.lucide.Sparkles
import com.composables.icons.lucide.Spline
import com.composables.icons.lucide.Swords
import com.terraplanistas.rolltogo.data.model.Playstyle

@Entity(tableName = "playstyle")
data class PlaystyleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val icon: String
)

fun PlaystyleEntity.toPlaystyle(): Playstyle{
    return Playstyle(
        id = this.id,
        title = this.title,
        description = this.description,
        icon = resolveIcon(this.icon)
    )
}
fun resolveIcon(name: String): ImageVector = when (name) {
    "swords" -> Lucide.Swords
    "sparkles" -> Lucide.Sparkles
    "shield_plus" -> Lucide.ShieldPlus
    "spline" -> Lucide.Spline
    "scale" -> Lucide.Scale
    else -> Lucide.CircleHelp
}


