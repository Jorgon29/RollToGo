package com.terraplanistas.rolltogo.data.database.entities.grants

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity

@Entity(
    tableName = "grant_option_items",

    primaryKeys = ["granter_option_set_id", "granted_content_id"]
)
data class GrantOptionItemsEntity(
    val granter_option_set_id: String,
    val granted_content_id: String
)