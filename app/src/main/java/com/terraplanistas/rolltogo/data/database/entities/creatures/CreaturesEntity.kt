package com.terraplanistas.rolltogo.data.database.entities.creatures

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "creatures",
    foreignKeys = [
        ForeignKey(
            entity = MonstersEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        ),
        ForeignKey(
            entity = CharactersEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        )
    ]
)
data class CreaturesEntity(
    @PrimaryKey val id: String,
    val name: String,
    val size_enum: String,
    val type_enum: String,
    val alignment_enum: String,
    val base_hp: Int,
    val base_ac: Int,
    val creature_source_type: String
)