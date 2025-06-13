package com.terraplanistas.rolltogo.data.model.character

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
