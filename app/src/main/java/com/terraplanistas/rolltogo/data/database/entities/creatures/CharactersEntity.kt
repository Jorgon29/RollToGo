package com.terraplanistas.rolltogo.data.database.entities.creatures

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "characters",
    foreignKeys = [
        ForeignKey(
            entity = CreaturesEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class CharactersEntity(
    @PrimaryKey val id: String,
    val race_id: String,
    val total_level: Int
)