package com.terraplanistas.rolltogo.data.database.entities.items

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "item_tags",
    primaryKeys = ["items_id", "tag"],
    foreignKeys = [
        ForeignKey(
            entity = ItemEntity::class,
            childColumns = ["items_id"],
            parentColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class ItemTagEntity(
    val items_id: String,
    val tag: String
)