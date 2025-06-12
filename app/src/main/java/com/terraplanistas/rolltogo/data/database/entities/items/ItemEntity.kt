package com.terraplanistas.rolltogo.data.database.entities.items

import androidx.compose.ui.text.font.FontWeight
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.ItemRarityEnum
import com.terraplanistas.rolltogo.data.enums.ItemTypeEnum

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
    val cost_gp: Double,
    val attunement_required: Boolean,
    val attunement: String,
    val it_magical: Boolean
)