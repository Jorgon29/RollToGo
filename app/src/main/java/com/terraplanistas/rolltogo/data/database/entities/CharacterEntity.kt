package com.terraplanistas.rolltogo.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val age: Int,
    val flaws: String,
    val biography: String,
    val personality: String,
    val appearance: String,
    val alignment: String
)