package com.terraplanistas.rolltogo.data.database.entities.grants

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity

@Entity(
    tableName = "grant_option_items",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["granted_content_id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = GrantOptionSetsEntity::class,
            parentColumns = ["id"],
            childColumns = ["granter_option_set_id"],
            onDelete = CASCADE
        )
    ],
    primaryKeys = ["granter_option_set_id", "granted_content_id"]
)
data class GrantOptionItemsEntity(
    val granter_option_set_id: String,
    val granted_content_id: String
)