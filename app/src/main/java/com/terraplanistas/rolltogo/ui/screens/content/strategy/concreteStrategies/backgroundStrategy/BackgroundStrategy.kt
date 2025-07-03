package com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.backgroundStrategy

import android.util.Log
import com.terraplanistas.rolltogo.data.enums.ProficiencyLevelEnum
import com.terraplanistas.rolltogo.data.enums.ProficiencyTypeEnum
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.data.repository.contentCreation.ContentCreationRepository
import com.terraplanistas.rolltogo.ui.screens.content.strategy.ContentStrategy

class BackgroundStrategy : ContentStrategy {
    override fun validateContent(data: Map<String, Any>): Boolean {
        return data.containsKey("name") &&
                data["name"] is String &&
                (data["name"] as String).isNotBlank() &&
                data.containsKey("description") &&
                data["description"] is String
    }

    override suspend fun sumbit(
        data: Map<String, Any>,
        repo: ContentCreationRepository
    ) {
      repo.createContent(data, SourceContentEnum.BACKGROUND)
    }

    override fun getDefaultData(): Map<String, Any> {
        return mutableMapOf(
            "name" to "",
            "description" to "",
            "feature_ids" to emptyList<String>(),
            "proficiency_data" to emptyList<Map<String, Any>>(),
        )
    }
}