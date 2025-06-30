package com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.featureStrategy

import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.data.repository.contentCreation.ContentCreationRepository
import com.terraplanistas.rolltogo.ui.screens.content.strategy.ContentStrategy

class FeatureStrategy: ContentStrategy {
    override fun validateContent(data: Map<String, Any>): Boolean {
        return data["name"].toString().isNotEmpty()
                && data["description"].toString().isNotEmpty()
    }

    override fun sumbit(
        data: Map<String, Any>,
        repo: ContentCreationRepository
    ) {
    repo.createContent(data, SourceContentEnum.FEATURES)
    }

    override fun getDefaultData(): Map<String, Any> {
        return mutableMapOf(
            "name" to "",
            "description" to "",
            "feature_type" to "",
            "is_magical" to false,
            "is_passive" to true,
            "action_type" to "",
            "damage" to "",
            "damage_type" to "",
            "bonus" to "",
            "bonus_type" to ""
        )
    }
}