package com.terraplanistas.rolltogo.data.model.creatures.character

import com.terraplanistas.rolltogo.data.database.entities.items.ItemEntity
import com.terraplanistas.rolltogo.data.enums.ItemRarityEnum
import com.terraplanistas.rolltogo.data.enums.ItemTypeEnum

data class DomainItem(
    val id: String,
    val name: String,
    val description: String,
    val item_type_enum: ItemTypeEnum,
    val rarity_enum: ItemRarityEnum,
    val weight: Int,
    val cost_value: Double,
    val cost_unit: String,
    val attunement_required: Boolean,
    val it_magical: Boolean
)

fun DomainItem.toItemEntity(): ItemEntity {
    return ItemEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        item_type_enum = this.item_type_enum,
        rarity_enum = this.rarity_enum,
        weight = this.weight,
        cost_value = this.cost_value,
        cost_unit = this.cost_unit,
        attunement_required = this.attunement_required,
        it_magical = this.it_magical
    )
}
