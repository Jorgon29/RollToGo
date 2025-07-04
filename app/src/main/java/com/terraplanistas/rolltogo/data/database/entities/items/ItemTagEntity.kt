package com.terraplanistas.rolltogo.data.database.entities.items

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(
    tableName = "item_tags",
    primaryKeys = ["items_id", "tag"],

)
data class ItemTagEntity(
    val items_id: String,
    val tag: String
)