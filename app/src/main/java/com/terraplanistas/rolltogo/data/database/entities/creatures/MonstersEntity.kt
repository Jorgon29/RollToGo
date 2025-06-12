package com.terraplanistas.rolltogo.data.database.entities.creatures

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "monsters",
    foreignKeys = [ForeignKey(
        entity = CreaturesEntity::class,
        parentColumns = ["id"],
        childColumns = ["id"],
        onDelete = CASCADE
    )]
)
data class MonstersEntity(
    @PrimaryKey val id: String,
    val challenge_rating: Double,
    val legendary: Boolean,
    val lair: String
)