package com.terraplanistas.rolltogo.data.database.entities.items

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.CurrencyEnum
import com.terraplanistas.rolltogo.data.enums.ItemTypeEnum
import com.terraplanistas.rolltogo.data.enums.RarityEnum
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainItem
import com.terraplanistas.rolltogo.helpers.typeConverter.EnumConverters
import java.math.BigDecimal
@Entity(
    tableName = "items",

)
data class ItemEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val item_type_enum: ItemTypeEnum,
    val rarity_enum: RarityEnum,
    val weight: BigDecimal,
    val cost_value: BigDecimal,
    val cost_unit: CurrencyEnum,
    val attunement_required: Boolean,
    val is_magical: Boolean
)

fun ItemEntity.toDomainItem(tags: List<String>, grantId: String): DomainItem {
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
        it_magical = this.is_magical,
        grantId = grantId
    )
}