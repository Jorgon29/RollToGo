package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity

@Entity(
    tableName = "background",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            childColumns = ["id"],
            parentColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class BackgroundEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String
)