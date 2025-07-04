package com.terraplanistas.rolltogo.ui.screens.featuresForum

import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.database.entities.features.FeaturesEntity

data class FeatureWithContent(
    val feature: FeaturesEntity,
    val content: ContentEntity
)