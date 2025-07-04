package com.terraplanistas.rolltogo.data.database.entities.spells

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.items.ItemEntity

@Entity(
    tableName = "spell_material",
    primaryKeys = ["item_id", "spell_id"],
    foreignKeys = [
        ForeignKey(
            entity = SpellEntity::class,
            parentColumns = ["id"],
            childColumns = ["spell_id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = ItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["item_id"],
            onDelete = CASCADE
        )
    ]
)
data class SpellMaterialEntity(
    val item_id: String,
    val spell_id: String,
    val consumed: Boolean
)