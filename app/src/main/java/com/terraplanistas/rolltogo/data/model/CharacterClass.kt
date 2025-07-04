package com.terraplanistas.rolltogo.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class CharacterClass(
    val id: Int,
    val name: String,
    val description: String,
    val icon: ImageVector
)
