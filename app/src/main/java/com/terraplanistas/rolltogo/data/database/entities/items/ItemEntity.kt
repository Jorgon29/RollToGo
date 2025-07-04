package com.terraplanistas.rolltogo.data.database.entities.items

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.CurrencyEnum
import com.terraplanistas.rolltogo.data.enums.ItemTypeEnum
import com.terraplanistas.rolltogo.data.enums.RarityEnum
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainItem
import java.math.BigDecimal
@Entity(
    tableName = "items",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            childColumns = ["id"],
            parentColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class ItemEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val item_type_enum: ItemTypeEnum,
    val weight1: Double,
    val rarity_enum: RarityEnum,
    val weight: BigDecimal,
    val cost_value: BigDecimal,
    val cost_unit: CurrencyEnum,
    val attunement_required: Boolean,
    val is_magical: Boolean,
    val contentId: Any,
    val itemType: String,
    val rarity: String,
    val costValue: Double,
    val costUnit: String,
    val requiresAttunement: Boolean,
    val isMagical: Boolean,
    val actionType: String
)

fun ItemEntity.toDomainItem(tags: List<String>, grantId: String): DomainItem {
    return DomainItem(
        id = this.id,
        name = this.name,
        description = this.description,
        item_type_enum = this.item_type_enum,
        rarity_enum = rarity_enum,
        weight = weight,
        cost_value = cost_value,
        cost_unit = cost_unit,
        attunement_required = attunement_required,
        it_magical = is_magical,
        grantId = grantId
    )
}