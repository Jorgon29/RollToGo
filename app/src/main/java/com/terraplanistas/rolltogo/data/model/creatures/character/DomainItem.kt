package com.terraplanistas.rolltogo.data.model.creatures.character

import com.terraplanistas.rolltogo.data.database.entities.items.ItemEntity
import com.terraplanistas.rolltogo.data.enums.CurrencyEnum
import com.terraplanistas.rolltogo.data.enums.ItemTypeEnum
import com.terraplanistas.rolltogo.data.enums.RarityEnum
import java.math.BigDecimal

data class DomainItem(
    val id: String,
    val name: String,
    val description: String,
    val item_type_enum: ItemTypeEnum,
    val rarity_enum: RarityEnum,
    val weight: BigDecimal,
    val cost_value: BigDecimal,
    val cost_unit: CurrencyEnum,
    val attunement_required: Boolean,
    val it_magical: Boolean,
    val grantId: String
)

fun DomainItem.toItemEntity(): ItemEntity {
    return ItemEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        item_type_enum = this.item_type_enum,
        weight1 = (itemData["weight"] as? BigDecimal)?.toDouble() ?: 0.0,
        rarity_enum = this.rarity_enum,
        weight = this.weight,
        cost_value = this.cost_value,
        cost_unit = this.cost_unit,
        attunement_required = this.attunement_required,
        is_magical = this.it_magical,
        contentId = contentId,
        itemType = itemData["item_type"].toString(),
        rarity = itemData["rarity"].toString(),
        costValue = (itemData["cost_value"] as? BigDecimal)?.toDouble() ?: 0.0,
        costUnit = itemData["cost_unit"].toString(),
        requiresAttunement = itemData["attunment"] as? Boolean ?: false,
        isMagical = itemData["is_magical"] as? Boolean ?: false
    )
}
