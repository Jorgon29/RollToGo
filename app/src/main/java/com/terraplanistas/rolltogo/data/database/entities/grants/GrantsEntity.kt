package com.terraplanistas.rolltogo.data.database.entities.grants

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity

@Entity(
    tableName = "grants",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["granter_content_id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["granted_content_id"],
            onDelete = CASCADE
        )
    ]
)
data class GrantsEntity(
    @PrimaryKey val id: Int,
    val granter_type_enum: String,
    val granter_content_id: String,
    val granted_type: String,
    val granted_content_id: String
)