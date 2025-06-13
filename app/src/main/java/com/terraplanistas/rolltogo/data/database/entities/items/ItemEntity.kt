package com.terraplanistas.rolltogo.data.database.entities.items

import androidx.compose.ui.text.font.FontWeight
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.ItemRarityEnum
import com.terraplanistas.rolltogo.data.enums.ItemTypeEnum
import com.terraplanistas.rolltogo.data.model.character.DomainItem

@Entity(
    tableName = "items",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            childColumns = ["id"],
            parentColumns = ["id"],
        )
    ]
)
data class ItemEntity(
    @PrimaryKey val id: String,
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



fun ItemEntity.toDomainItem(tags: List<String>): DomainItem {
    return DomainItem(
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