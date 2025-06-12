package com.terraplanistas.rolltogo.data.database.entities.items

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.enums.ItemModifierTypeEnum

@Entity(
    tableName = "item_modifier",
    foreignKeys = [
        ForeignKey(
            entity = ItemEntity::class,
            childColumns = ["item_id"],
            parentColumns = ["id"]
        )
    ]
)
data class ItemModifier(
    @PrimaryKey val id: String,
    val item_id: String,
    val modifier_type_enum: ItemModifierTypeEnum,
    val modifier_formula: String
)