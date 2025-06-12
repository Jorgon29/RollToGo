package com.terraplanistas.rolltogo.data.database.entities.features

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantOptionSetsEntity

@Entity(
    tableName = "special_die_progressions",
    foreignKeys = [
        ForeignKey(
            entity = SpecialDieEntity::class,
            parentColumns = ["id"],
            childColumns = ["special_die_id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = LevelProgressionsEntity::class,
            parentColumns = ["id"],
            childColumns = ["level_progressions"],
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
data class SpecialDieProgressionsEntity(
    @PrimaryKey val id: String,
    val special_die_id: String,
    val level_progressions: String,
    val die_formula: String,
    val grant_option_set_id: String
)