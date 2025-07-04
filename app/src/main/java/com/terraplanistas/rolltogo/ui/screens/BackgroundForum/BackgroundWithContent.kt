package com.terraplanistas.rolltogo.ui.screens.BackgroundForum

import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.BackgroundEntity

data class BackgroundWithContent(
    val background: BackgroundEntity,
    val content: ContentEntity
)