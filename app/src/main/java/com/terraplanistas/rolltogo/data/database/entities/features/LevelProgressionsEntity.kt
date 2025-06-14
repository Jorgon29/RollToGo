package com.terraplanistas.rolltogo.data.database.entities.features

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantOptionSetsEntity
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantsEntity

@Entity(
    tableName = "level_progressions",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            childColumns = ["id"],
            parentColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class LevelProgressionsEntity(
    @PrimaryKey val id: String,
    val level: Int,
    val new_progression_value: String
)