package com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.ItemStrategy

import com.terraplanistas.rolltogo.data.enums.ItemTypeEnum
import com.terraplanistas.rolltogo.data.enums.RarityEnum
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.data.repository.contentCreation.ContentCreationRepository
import com.terraplanistas.rolltogo.ui.screens.content.strategy.ContentStrategy
import java.math.BigDecimal

class WeaponStrategy : ContentStrategy {
    override fun validateContent(data: Map<String, Any>): Boolean {
        return data["name"].toString().isNotEmpty()
                && data["description"].toString().isNotEmpty()
                && data["item_type"] != null
                && data["rarity"] != null
                && data["weight"] != null
                && data["cost_value"] != null
                && data["cost_unit"] != null
                && data["attunment"] != null
                && data["is_magical"] != null
                && data["item_tag"] != null
                && data["attack_bonus"] != null
                && data["damage"].toString().isNotEmpty()
                && data["damage_tipe"].toString().isNotEmpty()

    }

    override fun sumbit(data: Map<String, Any>, repo: ContentCreationRepository) {
        repo.createContent(data, SourceContentEnum.ITEM)

    }

    override fun getDefaultData(): Map<String, Any> {
        return mapOf(
            "name" to "",
            "description" to "",
            "item_type" to "",
            "rarity" to "",
            "weight" to "",
            "cost_value" to "",
            "cost_unit" to "",
            "attunment" to false,
            "is_magical" to false,
            "item_tag" to emptyList<String>(),
            "attack_bonus" to "",
            "damage" to "",
            "damage_tipe" to ""
        )
    }


}