package com.terraplanistas.rolltogo.data.repository.contentCreation

import com.terraplanistas.rolltogo.data.database.entities.features.FeaturesEntity
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import kotlinx.coroutines.flow.Flow

interface ContentCreationRepository {
    fun createContent(content: Map<String, Any>, type: SourceContentEnum)

    suspend fun getFeatures(): List<FeaturesEntity>
}