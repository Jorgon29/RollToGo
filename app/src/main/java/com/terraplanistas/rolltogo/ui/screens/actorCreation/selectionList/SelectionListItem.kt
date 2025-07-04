package com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList

import androidx.compose.ui.graphics.vector.ImageVector

data class SelectionListItem(
    val icon: ImageVector,
    val name: String,
    val description: String? = null,
    val id: Int = 0
)
