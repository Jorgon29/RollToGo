package com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.backgroundStrategy

import com.terraplanistas.rolltogo.data.enums.ProficiencyLevelEnum
import com.terraplanistas.rolltogo.data.enums.ProficiencyTypeEnum
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
        try {

            val proficiencyData = (data["proficiency_data"] as? List<Map<String, Any>>)?.map {
                CreationProfitiency(
                    name = it["name"] as String,
                    type = ProficiencyLevelEnum.valueOf(it["type"] as String).toString(),
                    ability = it["ability"] as String,
                    proficiencyType = ProficiencyTypeEnum.valueOf(it["proficiency_type"] as String).toString())
            } ?: emptyList()

            println(
                """
                Guardando Background:
                Nombre: ${data["name"]}
                Descripción: ${data["description"]}
                Proficiencias: $proficiencyData
                Items IDs: ${data["item_ids"]}
            """.trimIndent()
            )

        } catch (e: Exception) {

        }
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