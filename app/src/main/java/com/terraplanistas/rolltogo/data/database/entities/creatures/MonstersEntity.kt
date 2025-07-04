package com.terraplanistas.rolltogo.data.database.entities.creatures

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "monsters",

)
data class MonstersEntity(
    @PrimaryKey val id: String,
    val challenge_rating: String,
    val legendary: Boolean,
    val lair: Boolean
)