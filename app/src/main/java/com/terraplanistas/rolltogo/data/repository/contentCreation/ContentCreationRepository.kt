package com.terraplanistas.rolltogo.data.repository.contentCreation

import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.database.entities.features.FeaturesEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.BackgroundEntity
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import kotlinx.coroutines.flow.Flow

interface ContentCreationRepository {
   suspend fun createContent(content: Map<String, Any>, type: SourceContentEnum)

    suspend fun getFeatures(): List<FeaturesEntity>
    suspend fun getContentsByType(type: SourceContentEnum): List<ContentEntity>
    suspend fun getFeatureByContentId(contentId: String): FeaturesEntity?
    suspend fun updateContent(content: ContentEntity)
    suspend fun updateFeature(feature: FeaturesEntity)
    suspend fun deleteContent(contentId: String)
    suspend fun getBackgroundByContentId(id: String) : BackgroundEntity?
     suspend  fun updateBackground(backGround: BackgroundEntity)
}