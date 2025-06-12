package com.terraplanistas.rolltogo.data.database.entities.features

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantOptionSetsEntity
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantsEntity

@Entity(
    tableName = "level_progressions",
    foreignKeys = [
        ForeignKey(
            entity = GrantsEntity::class,
            parentColumns = ["id"],
            childColumns = ["grant_id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = GrantOptionSetsEntity::class,
            parentColumns = ["id"],
            childColumns = ["grant_option_set_id"],
            onDelete = CASCADE
        )
    ]
)
data class LevelProgressionsEntity(
    @PrimaryKey val id: String,
    val level: Int,
    val level_progression_type: String,
    val grant_id: String,
    val grant_option_set_id: String,
)