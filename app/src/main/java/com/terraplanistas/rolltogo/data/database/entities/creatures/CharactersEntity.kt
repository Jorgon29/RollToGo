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
    val total_level: Int,
    val flaws: String,
    val biography: String,
    val appearance: String,
    val ideals: String,
    val age: String,
    val height: String,
    val weight: String,
    val personality: String,
    val gender: String,
    val skin: String,
    val hair: String,
    val faith: String,
    val eyes: String
)